package com.example.base.dto.chamado;

import com.example.base.model.Chamado;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChamadoMapper {

    Chamado toChamadoEntity(ChamadoReqDto dto);

    ChamadoRespDto toChamadoDto(Chamado chamado);
}
