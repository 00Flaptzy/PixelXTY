package com.PixelXTY.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PixelXTY.Service.AuthService;
import com.PixelXTY.Service.UsuarioService;
import com.PixelXTY.dto.request.LoginRequestDTO;
import com.PixelXTY.dto.request.UsuarioRequestDTO;
import com.PixelXTY.dto.response.LoginResponseDTO;
import com.PixelXTY.dto.response.UsuarioResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthService authService;

    // Registro
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> registrar(
            @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(dto));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "PixelXTY Backend");
        response.put("timestamp", new Date().toString());
        return ResponseEntity.ok(response);
    }
}
