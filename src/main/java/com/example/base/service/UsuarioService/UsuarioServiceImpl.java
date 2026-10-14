package com.example.base.service.UsuarioService;

import com.example.base.core.exception.APIException;
import com.example.base.core.exception.APIExceptionType;
import com.example.base.dao.UsuarioDao;
import com.example.base.dto.Usuario.UsuarioMapper;
import com.example.base.dto.Usuario.UsuarioReqDto;
import com.example.base.model.PapelUsuario;
import com.example.base.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;



    @Override
    @Transactional
    public void cadastrarUsuario(UsuarioReqDto usuarioReqDto) {
        if (this.usuarioDao.existsByEmail(usuarioReqDto.email())){
            throw new APIException("Já existe um usuário cadastrado com esse e-mail", APIExceptionType.CONFLICT_ERROR);
        }

        Usuario usuario = this.usuarioMapper.toUsuarioEntity(usuarioReqDto);
        usuario.setSenha(this.passwordEncoder.encode(usuarioReqDto.senha()));
        usuario.setPapel(PapelUsuario.USUARIO);
        usuarioDao.save(usuario);
    }


}
