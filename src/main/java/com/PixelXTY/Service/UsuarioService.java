package com.PixelXTY.Service;

import com.PixelXTY.dto.request.UsuarioRequestDTO;
import com.PixelXTY.dto.response.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO dto);
    UsuarioResponseDTO obtenerUsuarioPorId( Long id);
}
