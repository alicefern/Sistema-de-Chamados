package com.example.base.controller;

import com.example.base.dto.UsuarioRequest.UsuarioReqDto;
import com.example.base.dto.UsuarioRequest.UsuarioRespDto;
import com.example.base.service.UsuarioService.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarAdmin(@Valid @RequestBody  UsuarioReqDto usuarioDto){
        usuarioService.cadastrarAdmin(usuarioDto);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuarios(@Valid @RequestBody  UsuarioReqDto usuarioDto){
        usuarioService.cadastrarUsuario(usuarioDto);
    }

    @GetMapping("/admins")
    public ResponseEntity<Page<UsuarioRespDto>> listarAdmins(@PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable page){
        return ResponseEntity.ok(this.usuarioService.listarAdmins(page));
    }

}
