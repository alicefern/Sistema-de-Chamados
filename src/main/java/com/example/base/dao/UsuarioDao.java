package com.example.base.dao;

import com.example.base.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByPapel(String papel, Pageable page);
    Usuario findByEmail(String email);
}
