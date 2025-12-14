package com.PixelXTY.Service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.PixelXTY.Repository.UsuarioRepository;
import com.PixelXTY.Service.UsuarioService;
import com.PixelXTY.dto.request.UsuarioRequestDTO;
import com.PixelXTY.dto.response.UsuarioResponseDTO;
import com.PixelXTY.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder; // Inyectar, no crear

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRol("USER");

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(guardado.getId());
        response.setNombre(guardado.getNombre());
        response.setApellido(guardado.getApellidos());
        response.setEmail(guardado.getEmail());
        response.setRol(guardado.getRol());

        return response;
    }

    @Override
    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setApellido(usuario.getApellidos());
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol());

        return response;
    }
}