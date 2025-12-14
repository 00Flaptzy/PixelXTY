package com.PixelXTY.Service;

import com.PixelXTY.dto.request.LoginRequestDTO;
import com.PixelXTY.dto.response.LoginResponseDTO;

public interface AuthService {
     LoginResponseDTO login(LoginRequestDTO dto);
}
