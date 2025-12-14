package com.PixelXTY.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PixelXTY.Service.TareaService;
import com.PixelXTY.dto.request.TareaRequestDTO;
import com.PixelXTY.dto.response.TareaResponseDTO;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TareaController {

    private final TareaService tareaService;

    @PostMapping
    public ResponseEntity<TareaResponseDTO> crearTarea(
            @RequestBody TareaRequestDTO dto) {
        return ResponseEntity.ok(tareaService.crearTarea(dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TareaResponseDTO>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                tareaService.listarTareasPorUsuario(usuarioId));
    }

    @PutMapping("/{id}/completar")
    public ResponseEntity<Void> completarTarea(
            @PathVariable Long id) {
        tareaService.marcarComoCompletada(id);
        return ResponseEntity.ok().build();
    }
}
