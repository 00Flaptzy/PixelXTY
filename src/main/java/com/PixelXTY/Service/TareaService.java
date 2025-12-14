package com.PixelXTY.Service;

import java.util.List;

import com.PixelXTY.dto.request.TareaRequestDTO;
import com.PixelXTY.dto.response.TareaResponseDTO;

public interface TareaService {
    TareaResponseDTO crearTarea(TareaRequestDTO dto);

    List<TareaResponseDTO> listarTareasPorUsuario(Long usuarioId);

    void marcarComoCompletada(Long tareaId);
}
