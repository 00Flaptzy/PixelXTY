package com.PixelXTY.Service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.PixelXTY.Repository.UsuarioRepository;
import com.PixelXTY.Service.AuthService;
import com.PixelXTY.dto.request.LoginRequestDTO;
import com.PixelXTY.dto.response.LoginResponseDTO;
import com.PixelXTY.entity.Usuario;
import com.PixelXTY.exception.BadRequestException;
import com.PixelXTY.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder; // Inyectar, no crear
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            throw new BadRequestException("Credenciales incorrectas");
        }

        String token = jwtUtil.generarToken(usuario.getEmail());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol());
        response.setToken(token);

        return response;
    }
}