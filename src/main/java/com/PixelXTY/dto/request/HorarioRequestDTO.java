package com.PixelXTY.dto.request;

import java.time.LocalTime;

import lombok.Data;

@Data
public class HorarioRequestDTO {
    private String actividad;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long usuarioId;
}
