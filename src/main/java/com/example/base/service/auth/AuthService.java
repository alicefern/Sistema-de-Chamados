package com.example.base.service.auth;

import com.example.base.dto.Usuario.UsuarioLoginReqDto;

public interface AuthService {
    String login(UsuarioLoginReqDto dto);
}
