package com.example.base.service.auth;

import com.example.base.dto.Usuario.UsuarioLoginReqDto;
import com.example.base.core.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements  AuthService{

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public String login(UsuarioLoginReqDto dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.senha()));
        return this.jwtService.generateToken(authentication);
    }
}
