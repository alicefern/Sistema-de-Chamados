package com.example.base.service.UsuarioService;

import com.example.base.dto.Usuario.UsuarioReqDto;
import com.example.base.dto.Usuario.UsuarioRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    void cadastrarAdmin(UsuarioReqDto usuarioReqDto);
    Page<UsuarioRespDto> listarAdmins(Pageable page);
    UsuarioRespDto buscarUsuarioPorId(Long id);
}
