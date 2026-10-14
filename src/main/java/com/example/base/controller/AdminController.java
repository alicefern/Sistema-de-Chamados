package com.example.base.controller;

import com.example.base.dto.Usuario.UsuarioReqDto;
import com.example.base.dto.Usuario.UsuarioRespDto;
import com.example.base.service.UsuarioService.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("v1/admins")
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarAdmin(@Valid @RequestBody UsuarioReqDto usuarioDto){
        this.adminService.cadastrarAdmin(usuarioDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admins")
    public ResponseEntity<Page<UsuarioRespDto>> listarAdmins(@PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.ok(this.adminService.listarAdmins(page));
    }
}
