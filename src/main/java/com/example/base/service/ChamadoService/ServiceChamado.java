package com.example.base.service.ChamadoService;

import com.example.base.dto.chamadoRequest.ChamadoReqDto;
import com.example.base.dto.chamadoRequest.ChamadoRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceChamado {
    ChamadoRespDto cadastrarChamado(ChamadoReqDto chamadoReqDto, Long id);
    Page<ChamadoRespDto> listarChamadosAbertos(Pageable page, Long id);
}
