package com.PixelXTY.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.PixelXTY.Service.HorarioService;
import com.PixelXTY.Service.TareaService;
import com.PixelXTY.dto.response.HorarioResponseDTO;
import com.PixelXTY.dto.response.TareaResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final TareaService tareaService;
    private final HorarioService horarioService;

    @GetMapping("/{usuarioId}")
    public Map<String, Object> cargarDashboard(
            @PathVariable Long usuarioId) {

        List<TareaResponseDTO> tareas =
                tareaService.listarTareasPorUsuario(usuarioId);
        List<HorarioResponseDTO> horarios =
                horarioService.listarHorariosPorUsuario(usuarioId);

        long completadas = tareas.stream()
                .filter(TareaResponseDTO::getCompletada)
                .count();

        Map<String, Object> response = new HashMap<>();
        response.put("tareas", tareas);
        response.put("horarios", horarios);
        response.put("totalTareas", tareas.size());
        response.put("tareasCompletadas", completadas);
        response.put("porcentajeAvance",
                tareas.isEmpty() ? 0 : (completadas * 100 / tareas.size()));

        return response;
    }
}
