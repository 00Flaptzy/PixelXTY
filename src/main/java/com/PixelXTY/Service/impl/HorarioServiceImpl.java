package com.PixelXTY.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PixelXTY.Repository.HorarioRepository;
import com.PixelXTY.Repository.UsuarioRepository;
import com.PixelXTY.Service.HorarioService;
import com.PixelXTY.dto.request.HorarioRequestDTO;
import com.PixelXTY.dto.response.HorarioResponseDTO;
import com.PixelXTY.entity.Horario;
import com.PixelXTY.entity.Usuario;
import com.PixelXTY.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public HorarioResponseDTO crearHorario(HorarioRequestDTO dto) {
        String email = SecurityUtils.getEmailUsuarioAutenticado();
        
        if (email == null) {
            throw new RuntimeException("Usuario no autenticado");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Horario horario = new Horario();
        horario.setActividad(dto.getActividad());
        horario.setDia(dto.getDia());
        horario.setHoraInicio(dto.getHoraInicio());
        horario.setHoraFin(dto.getHoraFin());
        horario.setUsuario(usuario);

        Horario guardado = horarioRepository.save(horario);

        HorarioResponseDTO response = new HorarioResponseDTO();
        response.setId(guardado.getId());
        response.setActividad(guardado.getActividad());
        response.setDia(guardado.getDia());
        response.setHoraInicio(guardado.getHoraInicio());
        response.setHoraFin(guardado.getHoraFin());

        return response;
    }

    @Override
    public List<HorarioResponseDTO> listarHorariosPorUsuario(Long usuarioId) {
        return horarioRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(h -> {
                    HorarioResponseDTO dto = new HorarioResponseDTO();
                    dto.setId(h.getId());
                    dto.setActividad(h.getActividad());
                    dto.setDia(h.getDia());
                    dto.setHoraInicio(h.getHoraInicio());
                    dto.setHoraFin(h.getHoraFin());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}