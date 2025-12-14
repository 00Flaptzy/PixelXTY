package com.PixelXTY.dto.response;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data 
public class HorarioResponseDTO {
    private Long id;
    private String actividad;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<TareaResponseDTO> tareas;

}
