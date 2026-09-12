package com.example.base.dto.chamadoRequest;

import com.example.base.model.CategoriaChamado;
import com.example.base.model.PrioridadeChamado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChamadoReqDto(

        @NotBlank(message = "O nome deve ser informado")
        @Size(min = 5, max = 100, message = "O título deve conter no minímo 5 caracteres e no máximo 100")
        String titulo,

        @NotBlank(message = "A decrição deve ser informada")
        @Size(min = 10, max = 1000, message = "A descrição deve conter no minímo 10 caracteres e no máximo 1000")
        String descricao,

        @NotNull(message = "A categoria deve ser informada")
        CategoriaChamado categoria,

        @NotNull(message = "A prioridade deve ser informada")
        PrioridadeChamado prioridade) {
}
