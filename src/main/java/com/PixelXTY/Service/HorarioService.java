package com.PixelXTY.Service;

import java.util.List;

import com.PixelXTY.dto.request.HorarioRequestDTO;
import com.PixelXTY.dto.response.HorarioResponseDTO;

public interface HorarioService {
    HorarioResponseDTO crearHorario(HorarioRequestDTO dto);

    List<HorarioResponseDTO> listarHorariosPorUsuario(Long usuarioId);
}
