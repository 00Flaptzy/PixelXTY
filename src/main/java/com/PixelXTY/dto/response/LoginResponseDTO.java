package com.PixelXTY.dto.response;

import lombok.Data;

@Data 
public class LoginResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    private String token;
}
