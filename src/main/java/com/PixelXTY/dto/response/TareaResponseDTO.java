package com.PixelXTY.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data 
public class TareaResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaLimite;
    private String prioridad;
    private Boolean completada;
}
