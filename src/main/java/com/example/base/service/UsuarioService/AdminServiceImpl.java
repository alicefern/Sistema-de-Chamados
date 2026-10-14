package com.example.base.service.UsuarioService;

import com.example.base.core.exception.APIException;
import com.example.base.core.exception.APIExceptionType;
import com.example.base.dao.UsuarioDao;
import com.example.base.dto.Usuario.UsuarioMapper;
import com.example.base.dto.Usuario.UsuarioReqDto;
import com.example.base.dto.Usuario.UsuarioRespDto;
import com.example.base.model.PapelUsuario;
import com.example.base.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UsuarioDao usuarioDao;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void cadastrarAdmin(UsuarioReqDto usuarioReqDto) {
        if (this.usuarioDao.existsByEmail(usuarioReqDto.email())){
            throw new APIException("Já existe um usuário cadastrado com esse e-mail", APIExceptionType.CONFLICT_ERROR);
        }

        Usuario usuario = this.usuarioMapper.toUsuarioEntity(usuarioReqDto);
        usuario.setSenha(this.passwordEncoder.encode(usuarioReqDto.senha()));
        usuario.setPapel(PapelUsuario.ADMIN);
        usuarioDao.save(usuario);
    }

    @Override
    public Page<UsuarioRespDto> listarAdmins(Pageable page) {

        // 1. Define o limite máximo permitido
        int maxSize = 50;
        int size = Math.min(page.getPageSize(), maxSize);

        // 2. Recria o Pageable com o tamanho seguro
        Pageable paginacaoSegura = PageRequest.of(
                page.getPageNumber(),
                size,
                page.getSort()
        );

        // 3. Executa a busca no repositório usando a paginação protegida
        return usuarioDao.findByPapel("admin", paginacaoSegura).
                map(this.usuarioMapper::toUsuarioDto);
    }

    @Override
    public UsuarioRespDto buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioDao.findById(id).orElseThrow(()-> new APIException("Usuário não encontrado", APIExceptionType.NOT_FOUND));

        return this.usuarioMapper.toUsuarioDto(usuario);
    }

}
