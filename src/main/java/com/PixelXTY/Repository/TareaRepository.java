package com.PixelXTY.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PixelXTY.entity.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {
    List<Tarea> findByUsuarioId(Long usuarioId);
    List<Tarea> findByHorarioId(Long horarioId);
    List<Tarea> findByUsuarioIdAndCompletada(Long usuarioId, Boolean completada);
    List<Tarea> findByUsuarioIdAndPrioridad(Long usuarioId, String prioridad);

    List<Tarea> findByCompletadaFalseAndFechaLimiteBetween(
    LocalDateTime inicio,
    LocalDateTime fin
);

}
