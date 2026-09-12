package com.example.base.dto.chamadoRequest;

import com.example.base.model.CategoriaChamado;
import com.example.base.model.PrioridadeChamado;
import com.example.base.model.StatusChamado;

public record ChamadoRespDto(Long id, String titulo, String descricao, PrioridadeChamado prioridade, StatusChamado status, CategoriaChamado categoria) {
}
