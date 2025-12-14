package com.PixelXTY.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PixelXTY.Service.HorarioService;
import com.PixelXTY.dto.request.HorarioRequestDTO;
import com.PixelXTY.dto.response.HorarioResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HorarioController {

    private final HorarioService horarioService;

    @PostMapping
    public ResponseEntity<HorarioResponseDTO> crearHorario(
            @RequestBody HorarioRequestDTO dto) {
        return ResponseEntity.ok(horarioService.crearHorario(dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HorarioResponseDTO>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                horarioService.listarHorariosPorUsuario(usuarioId));
    }
}
