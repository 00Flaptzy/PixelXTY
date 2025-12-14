package com.PixelXTY.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.PixelXTY.Repository.TareaRepository;
import com.PixelXTY.entity.Tarea;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlertaScheduler {

    private final TareaRepository tareaRepository;

    // Cada 1 hora
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void revisarTareasProximas() {

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = ahora.plusHours(24);
        List<Tarea> tareas =
    tareaRepository.findByCompletadaFalseAndFechaLimiteBetween(ahora, limite);

    
        tareas.stream()
            .filter(t -> !t.getCompletada())
            .filter(t -> t.getFechaLimite() != null)
            .filter(t -> t.getFechaLimite().isAfter(ahora)
                      && t.getFechaLimite().isBefore(limite))
            .forEach(t ->
                System.out.println(
                    "⚠ ALERTA: Tarea próxima a vencer → " + t.getTitulo()
                )
            );
    }
}
