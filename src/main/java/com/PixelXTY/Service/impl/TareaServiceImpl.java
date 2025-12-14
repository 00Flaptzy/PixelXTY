package com.PixelXTY.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PixelXTY.Repository.HorarioRepository;
import com.PixelXTY.Repository.TareaRepository;
import com.PixelXTY.Repository.UsuarioRepository;
import com.PixelXTY.Service.TareaService;
import com.PixelXTY.dto.request.TareaRequestDTO;
import com.PixelXTY.dto.response.TareaResponseDTO;
import com.PixelXTY.entity.Horario;
import com.PixelXTY.entity.Tarea;
import com.PixelXTY.entity.Usuario;
import com.PixelXTY.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HorarioRepository horarioRepository;

    @Override
    public TareaResponseDTO crearTarea(TareaRequestDTO dto) {
        String email = SecurityUtils.getEmailUsuarioAutenticado();
        
        if (email == null) {
            throw new RuntimeException("Usuario no autenticado");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Horario horario = null;
        if (dto.getHorarioId() != null) {
            horario = horarioRepository.findById(dto.getHorarioId())
                    .orElseThrow(() -> new RuntimeException("Horario no existe"));
        }

        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaLimite(dto.getFechaLimite());
        tarea.setPrioridad(dto.getPrioridad());
        tarea.setCompletada(false);
        tarea.setUsuario(usuario);
        tarea.setHorario(horario);

        Tarea guardada = tareaRepository.save(tarea);

        TareaResponseDTO response = new TareaResponseDTO();
        response.setId(guardada.getId());
        response.setTitulo(guardada.getTitulo());
        response.setDescripcion(guardada.getDescripcion());
        response.setFechaLimite(guardada.getFechaLimite());
        response.setPrioridad(guardada.getPrioridad());
        response.setCompletada(guardada.getCompletada());

        return response;
    }

    @Override
    public List<TareaResponseDTO> listarTareasPorUsuario(Long usuarioId) {
        // Verificar que el usuario autenticado pueda ver solo sus tareas
        String emailAutenticado = SecurityUtils.getEmailUsuarioAutenticado();
        
        return tareaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(t -> {
                    TareaResponseDTO dto = new TareaResponseDTO();
                    dto.setId(t.getId());
                    dto.setTitulo(t.getTitulo());
                    dto.setDescripcion(t.getDescripcion());
                    dto.setFechaLimite(t.getFechaLimite());
                    dto.setPrioridad(t.getPrioridad());
                    dto.setCompletada(t.getCompletada());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void marcarComoCompletada(Long tareaId) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        tarea.setCompletada(true);
        tareaRepository.save(tarea);
    }
}