package com.example.base.dto.Usuario;

import com.example.base.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {

    Usuario toUsuarioEntity(UsuarioReqDto dto);

    UsuarioRespDto toUsuarioDto(Usuario usuario);
}
