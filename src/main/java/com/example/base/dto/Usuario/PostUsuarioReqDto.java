package com.example.base.dto.Usuario;

public record PostUsuarioReqDto(
        String nome,
        String username,
        String senha,
        String confirmacaoSenha,
        String email,
        Long id
) {
}
