package com.PixelXTY.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data 
public class TareaRequestDTO {
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaLimite;
    private String prioridad;
    private Boolean completada;
    private Long usuarioId;
    private Long horarioId;
}
