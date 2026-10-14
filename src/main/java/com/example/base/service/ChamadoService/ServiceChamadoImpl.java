package com.example.base.service.ChamadoService;

import com.example.base.core.exception.APIException;
import com.example.base.core.exception.APIExceptionType;
import com.example.base.dao.ChamadoDao;
import com.example.base.dao.UsuarioDao;

import com.example.base.dto.chamado.ChamadoMapper;
import com.example.base.dto.chamado.ChamadoReqDto;
import com.example.base.dto.chamado.ChamadoRespDto;
import com.example.base.model.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceChamadoImpl implements ServiceChamado{

    private final UsuarioDao usuarioDao;
    private final ChamadoDao chamadoDao;
    private final ChamadoMapper chamadoMapper;

    @Override
    public ChamadoRespDto cadastrarChamado(ChamadoReqDto chamadoReqDto, Long id) {
        Usuario usuario = usuarioDao.findById(id)
                .orElseThrow(() -> new APIException("Usuário não encontrado", APIExceptionType.NOT_FOUND));

        Chamado chamado = this.chamadoMapper.toChamadoEntity(chamadoReqDto);
        chamado.setUsuario(usuario);
        chamado.setStatus(StatusChamado.ABERTO);
        chamadoDao.save(chamado);

        return this.chamadoMapper.toChamadoDto(chamado);

    }

    @Override
    public Page<ChamadoRespDto> listarChamados(Pageable page, Long id, StatusChamado statusChamado) {
        int maxSize = 50;
        int size = Math.min(page.getPageSize(), maxSize);

        Pageable paginacaoSegura = PageRequest.of(
                page.getPageNumber(),
                size,
                page.getSort()
        );

        Page<Chamado> chamados;

        if (statusChamado == null){
            chamados = chamadoDao.findByUsuarioId(id, paginacaoSegura);
        } else{
            chamados = chamadoDao.findAllByStatusAndUsuario_id(statusChamado, id, paginacaoSegura);
        }

        return chamados.map(this.chamadoMapper::toChamadoDto);


    }

    @Override
    public ChamadoRespDto detalharChamado(Long idUsuario, Long idChamado) {
        Usuario usuario = this.usuarioDao.findById(idUsuario)
                .orElseThrow(()-> new APIException("Usuário não encontrado",APIExceptionType.NOT_FOUND));

        Chamado chamado = this.chamadoDao.findById(idChamado)
                .orElseThrow(()-> new APIException("Chamado não encontrado",APIExceptionType.NOT_FOUND));

        if (usuario.getPapel() == PapelUsuario.USUARIO && !chamado.getUsuario().getId().equals(usuario.getId())){
            throw new APIException("Sem permissão para acessar esse chamado", APIExceptionType.FORBIDDEN);
        }

        return this.chamadoMapper.toChamadoDto(chamado);
    }

    @Override
    public Page<ChamadoRespDto> listarChamadosAdmin(Pageable page, StatusChamado status, CategoriaChamado categoria) {
        int maxSize = 50;
        int size = Math.min(page.getPageSize(), maxSize);

        Pageable paginacaoSegura = PageRequest.of(
                page.getPageNumber(),
                size,
                page.getSort()
        );

        Page<Chamado> chamados;

        if (status != null && categoria != null) {
            chamados = this.chamadoDao.findAllByStatusAndCategoria(status, categoria, paginacaoSegura);
        } else if (status != null) {
            chamados = this.chamadoDao.findAllByStatus(status, paginacaoSegura);
        } else if (categoria != null) {
            chamados = this.chamadoDao.findAllByCategoria(categoria, paginacaoSegura);
        } else {
            chamados = this.chamadoDao.findAll(paginacaoSegura);
        }

        return chamados.map(this.chamadoMapper::toChamadoDto);
    }

}
