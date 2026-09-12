package com.example.base.service.UsuarioService;

import com.example.base.dto.UsuarioRequest.UsuarioReqDto;
import com.example.base.dto.UsuarioRequest.UsuarioRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UsuarioService {
    void cadastrarAdmin(UsuarioReqDto usuarioReqDto);
    void cadastrarUsuario(UsuarioReqDto usuarioReqDto);
    Page<UsuarioRespDto> listarAdmins(Pageable page);
    UsuarioRespDto buscarUsuarioPorId(Long id);
}
