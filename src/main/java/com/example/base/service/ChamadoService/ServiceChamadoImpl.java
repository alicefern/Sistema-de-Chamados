package com.example.base.service.ChamadoService;

import com.example.base.core.exception.APIException;
import com.example.base.core.exception.APIExceptionType;
import com.example.base.dao.ChamadoDao;
import com.example.base.dao.UsuarioDao;

import com.example.base.dto.chamadoRequest.ChamadoReqDto;
import com.example.base.dto.chamadoRequest.ChamadoRespDto;
import com.example.base.model.Chamado;
import com.example.base.model.StatusChamado;
import com.example.base.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ServiceChamadoImpl implements ServiceChamado{

    private final UsuarioDao usuarioDao;
    private final ChamadoDao chamadoDao;

    @Override
    public ChamadoRespDto cadastrarChamado(ChamadoReqDto chamadoReqDto, Long id) {
        Usuario usuario = usuarioDao.findById(id)
                .orElseThrow(() -> new APIException("Usuário não encontrado", APIExceptionType.NOT_FOUND));

        Chamado chamado = new Chamado();
        chamado.setUsuario(usuario);
        chamado.setTitulo(chamadoReqDto.titulo());
        chamado.setDescricao(chamadoReqDto.descricao());
        chamado.setPrioridade(chamadoReqDto.prioridade());
        chamado.setStatus(StatusChamado.ABERTO);
        chamado.setCategoria(chamadoReqDto.categoria());
        chamadoDao.save(chamado);

        return new ChamadoRespDto(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getPrioridade(),
                chamado.getStatus(),
                chamado.getCategoria()
        );

    }

    @Override
    public Page<ChamadoRespDto> listarChamadosAbertos(Pageable page, Long id) {
        int maxSize = 50;
        int size = Math.min(page.getPageSize(), maxSize);

        Pageable paginacaoSegura = PageRequest.of(
                page.getPageNumber(),
                size,
                page.getSort()
        );

        return chamadoDao.findAllByStatusAndUsuario_id(StatusChamado.ABERTO, id, paginacaoSegura).
                map(chamado -> new ChamadoRespDto(
                        chamado.getId(),
                        chamado.getTitulo(),
                        chamado.getDescricao(),
                        chamado.getPrioridade(),
                        chamado.getStatus(),
                        chamado.getCategoria()));
    }


}
