package com.example.base.controller;

import com.example.base.dto.Usuario.UsuarioLogado;
import com.example.base.dto.chamado.ChamadoReqDto;
import com.example.base.dto.chamado.ChamadoRespDto;
import com.example.base.model.CategoriaChamado;
import com.example.base.model.StatusChamado;
import com.example.base.service.ChamadoService.ServiceChamado;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/v1/chamados")
public class ChamadoController {

    private final ServiceChamado serviceChamado;

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @PostMapping("/usuarios/chamados")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarChamado(@AuthenticationPrincipal UsuarioLogado dto, @Valid @RequestBody ChamadoReqDto chamadoReqDto){
        serviceChamado.cadastrarChamado(chamadoReqDto, dto.id());
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamados(
            @PageableDefault(size = 10, sort = "titulo", direction = Sort.Direction.ASC) Pageable page,
            @AuthenticationPrincipal UsuarioLogado dto,
            @RequestParam(name = "status", required = false) StatusChamado statusChamado){
        return ResponseEntity.ok(this.serviceChamado.listarChamados(page, dto.id(), statusChamado));
    }

    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    @GetMapping("/chamados/{id}")
    public ChamadoRespDto detalharChamado(
            @AuthenticationPrincipal UsuarioLogado dto,
            @PathVariable Long id
    ){
        return this.serviceChamado.detalharChamado(dto.id(), id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public Page<ChamadoRespDto> listarChamadosAdmin(
            @PageableDefault(size = 10, sort = "titulo", direction = Sort.Direction.ASC) Pageable page,
            @RequestParam(name = "status", required = false) StatusChamado statusChamado,
            @RequestParam(name = "categoria", required = false) CategoriaChamado categoriaChamado
            ){
        return this.serviceChamado.listarChamadosAdmin(page,statusChamado, categoriaChamado);

    }

}
