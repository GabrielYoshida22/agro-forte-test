package com.github.agro.adapter.inbound.controller;

import com.github.agro.adapter.inbound.DTO.PersonPhysicalRequestDTO;
import com.github.agro.core.domain.PersonPhysicalDomain;
import com.github.agro.core.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas/fisicas")
@Tag(name = "Pessoa Física", description = "Operações relacionadas ao cadastro de pessoas físicas")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cadastrar uma nova pessoa física",
            description = "Cadastra uma nova pessoa física no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa física cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (dados faltando ou incorretos)"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<PersonPhysicalDomain> cadastrarPessoaFisica(
            @RequestBody @Valid PersonPhysicalRequestDTO dto) {
        var pessoa = service.cadastrarPessoaFisica(dto);
        return ResponseEntity.ok(pessoa);
    }
}
