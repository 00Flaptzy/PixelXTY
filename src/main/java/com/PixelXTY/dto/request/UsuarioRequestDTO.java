package com.PixelXTY.dto.request;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
}
