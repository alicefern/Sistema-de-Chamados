package com.example.base.dto.Usuario;

import com.example.base.model.PapelUsuario;

public record UsuarioRespDto(Long id, String nome, String email, PapelUsuario papel) {
}
