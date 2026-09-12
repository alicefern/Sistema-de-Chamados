package com.example.base.dto.UsuarioRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioReqDto(

        @NotBlank(message = "O nome deve ser informado")
        @Size(min = 3, max = 100, message = "O nome deve ter no minímo 3 caracteres e no máximo 100")
        String nome,

        @NotBlank(message = "O e-mail deve ser informado")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha deve ser informada")
        @Size(min = 8, message = "A senha precisa ter no minímo 8 caracteres")
        String senha) {

}
