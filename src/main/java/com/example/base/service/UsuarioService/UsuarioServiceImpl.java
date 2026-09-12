package com.example.base.service.UsuarioService;

import com.example.base.core.exception.APIException;
import com.example.base.core.exception.APIExceptionType;
import com.example.base.dao.UsuarioDao;
import com.example.base.dto.UsuarioRequest.UsuarioReqDto;
import com.example.base.dto.UsuarioRequest.UsuarioRespDto;
import com.example.base.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;

    @Override
    @Transactional
    public void cadastrarAdmin(UsuarioReqDto usuarioReqDto) {
        if (usuarioDao.findByEmail(usuarioReqDto.email()) != null) {
            throw new APIException("Já existe um usuário com esse e-mail", APIExceptionType.CONFLICT_ERROR);
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioReqDto.nome());
        usuario.setEmail(usuarioReqDto.email());
        usuario.setSenha(usuarioReqDto.senha());
        usuario.setPapel("admin");
        usuarioDao.save(usuario);
    }

    @Override
    public void cadastrarUsuario(UsuarioReqDto usuarioReqDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioReqDto.nome());
        usuario.setEmail(usuarioReqDto.email());
        usuario.setSenha(usuarioReqDto.senha());
        usuario.setPapel("usuario");
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
                map(usuario ->
                        new UsuarioRespDto(
                                usuario.getId(),
                                usuario.getNome(),
                                usuario.getEmail(),
                                usuario.getPapel()));
    }

    @Override
    public UsuarioRespDto buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioDao.findById(id);

        return new UsuarioRespDto(usuario.get().getId(),
                                usuario.get().getNome(),
                                usuario.get().getEmail(),
                                usuario.get().getPapel());
    }
}
