package com.example.viajouapi.controllers;

import com.example.viajouapi.models.PesquisaPerfil;
import com.example.viajouapi.services.PesquisaPerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/pesquisas")
public class PesquisaPerfilController {

    private final PesquisaPerfilService pesquisaPerfilService;

    public PesquisaPerfilController(PesquisaPerfilService pesquisaPerfilService) {
        this.pesquisaPerfilService = pesquisaPerfilService;
    }

    @Operation(summary = "Buscar todas as pesquisas de perfil", description = "Retorna uma lista com todas as pesquisas de perfil cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisas recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<PesquisaPerfil> buscarPesquisas() {
        return pesquisaPerfilService.buscarPesquisas();
    }

    @Operation(summary = "Buscar pesquisa de perfil por ID", description = "Busca uma pesquisa de perfil específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisa recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pesquisa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PesquisaPerfil> buscarPesquisaPorId(@PathVariable Long id) {
        PesquisaPerfil pesquisa = pesquisaPerfilService.buscarPesquisaPorId(id);
        return ResponseEntity.ok(pesquisa);
    }

    @Operation(summary = "Buscar pesquisas de perfil por UID do usuário", description = "Busca pesquisas de perfil associadas a um usuário específico pelo UID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pesquisas recuperadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar/usuario/{uid}")
    public PesquisaPerfil buscarPesquisasPorUidUsuario(@PathVariable String uid) {
        return pesquisaPerfilService.buscarPesquisasPorUidUsuario(uid);
    }

    @Operation(summary = "Inserir uma nova pesquisa de perfil", description = "Insere uma nova resposta de pesquisa de perfil no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pesquisa inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/inserir")
    public ResponseEntity<?> inserirPesquisa(@Valid @RequestBody PesquisaPerfil pesquisaPerfil, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
        }

        pesquisaPerfilService.salvarPesquisa(pesquisaPerfil);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pesquisa inserida com sucesso");
    }
}
