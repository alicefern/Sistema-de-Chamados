package com.example.base.controller;

import com.example.base.dto.chamadoRequest.ChamadoReqDto;
import com.example.base.dto.chamadoRequest.ChamadoRespDto;
import com.example.base.service.ChamadoService.ServiceChamado;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/v1/chamados")
public class ChamadoController {

    private ServiceChamado serviceChamado;

    @PostMapping("/usuarios/{usuarioId}/chamados")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarChamado(@PathVariable Long usuarioId, @Valid @RequestBody ChamadoReqDto chamadoReqDto){
        serviceChamado.cadastrarChamado(chamadoReqDto, usuarioId);
    }

    @GetMapping("/usuarios/{usuarioId}/chamados/abertos")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadosAbertos(@PageableDefault(size = 10, sort = "titulo", direction = Sort.Direction.ASC) Pageable page, @PathVariable Long usuarioId){
        return ResponseEntity.ok(this.serviceChamado.listarChamadosAbertos(page, usuarioId));
    }

}
