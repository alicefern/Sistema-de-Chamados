package com.example.base.controller;

import com.example.base.dto.Usuario.UsuarioLoginReqDto;
import com.example.base.dto.Usuario.UsuarioReqDto;
import com.example.base.service.UsuarioService.UsuarioService;
import com.example.base.service.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody UsuarioReqDto dto){
        this.usuarioService.cadastrarUsuario(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioLoginReqDto dto){
        return this.authService.login(dto) ;
    }
}
