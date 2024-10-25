package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Empresa;
import com.example.viajouapi.services.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // Buscar todas as empresas
    @Operation(summary = "Buscar todas as empresas", description = "Retorna a lista de todas as empresas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresas retornadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<Empresa> buscarEmpresas() {
        return empresaService.buscarEmpresas();
    }

    // Buscar empresa por ID
    @Operation(summary = "Buscar empresa por ID", description = "Retorna uma empresa pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarEmpresaPorID(id);
        return ResponseEntity.ok(empresa);
    }

    // Inserir uma nova empresa
    @Operation(summary = "Inserir uma nova empresa", description = "Insere uma nova empresa no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirEmpresa(@Valid @RequestBody Empresa empresa, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        empresaService.salvarEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body("Empresa inserida com sucesso");
    }

    // Atualizar parcialmente uma empresa
    @Operation(summary = "Atualizar parcialmente uma empresa", description = "Atualiza algumas informações da empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Empresa empresaExistente = empresaService.buscarEmpresaPorID(id);
        if (empresaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nome":
                    empresaExistente.setNome((String) valor);
                    break;
                case "siteEmpresa":
                    empresaExistente.setSiteEmpresa((String) valor);
                    break;
                case "dataDesativacao":
                    empresaExistente.setDataDesativacao(ZonedDateTime.parse((String) valor));
                    break;
            }
        });

        empresaService.salvarEmpresa(empresaExistente);
        return ResponseEntity.ok("Empresa atualizada com sucesso");
    }
}
