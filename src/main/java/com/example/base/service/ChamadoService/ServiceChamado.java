package com.example.base.service.ChamadoService;

import com.example.base.dto.chamado.ChamadoReqDto;
import com.example.base.dto.chamado.ChamadoRespDto;
import com.example.base.model.CategoriaChamado;
import com.example.base.model.StatusChamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceChamado {
    ChamadoRespDto cadastrarChamado(ChamadoReqDto chamadoReqDto, Long id);
    Page<ChamadoRespDto> listarChamados(Pageable page, Long id, StatusChamado statusChamado);
    ChamadoRespDto detalharChamado(Long idUsuario, Long idChamado);
    Page<ChamadoRespDto> listarChamadosAdmin(Pageable page, StatusChamado status, CategoriaChamado categoria);

}
