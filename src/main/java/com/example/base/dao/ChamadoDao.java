package com.example.base.dao;

import com.example.base.model.Chamado;
import com.example.base.model.StatusChamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoDao extends JpaRepository<Chamado, Long> {
    Page<Chamado> findAllByStatusAndUsuario_id(StatusChamado status, Long id, Pageable page);
}
