package com.example.viajouapi.controllers;

import com.example.viajouapi.models.PlanoUsuario;
import com.example.viajouapi.services.PlanoUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/planos-usuario")
public class PlanoUsuarioController {
    private final PlanoUsuarioService planoUsuarioService;

    public PlanoUsuarioController(PlanoUsuarioService planoUsuarioService) {
        this.planoUsuarioService = planoUsuarioService;
    }

    @Operation(summary = "Buscar todos os planos de usuários", description = "Retorna uma lista de todos os planos de usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planos de usuários recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<PlanoUsuario> buscarPlanosUsuarios() {
        return planoUsuarioService.buscarTodos();
    }

    @Operation(summary = "Buscar planos de usuário por UID", description = "Retorna uma lista de planos associados a um usuário específico pelo UID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planos do usuário recuperados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/buscar-por-usuario/{uid}")
    public List<PlanoUsuario> buscarPorUsuario(@PathVariable String uid) {
        return planoUsuarioService.buscarPorUsuarioUid(uid);
    }

    @Operation(summary = "Buscar planos ativos de usuários", description = "Retorna planos de usuários que estão ativos, com data de término posterior à data atual")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planos ativos recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/ativos")
    public List<PlanoUsuario> buscarPlanosAtivos() {
        return planoUsuarioService.buscarPlanosAtivos();
    }

    @Operation(summary = "Inserir um novo plano de usuário", description = "Insere um novo plano de usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de usuário inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirPlanoUsuario(@Valid @RequestBody PlanoUsuario planoUsuario, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            planoUsuarioService.salvarPlanoUsuario(planoUsuario);
            return ResponseEntity.ok("Plano de usuário inserido com sucesso");
        }
    }

    @Operation(summary = "Atualizar parcialmente um plano de usuário", description = "Atualiza parcialmente as informações de um plano de usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano de usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        PlanoUsuario planoUsuarioExistente = planoUsuarioService.buscarPorID(id);
        if (planoUsuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de usuário não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "dataPagamento":
                    planoUsuarioExistente.setDataPagamento(LocalDateTime.parse((String) valor));
                    break;
                case "dataTermino":
                    planoUsuarioExistente.setDataTermino(LocalDateTime.parse((String) valor));
                    break;
            }
        });

        planoUsuarioService.salvarPlanoUsuario(planoUsuarioExistente);
        return ResponseEntity.ok("Plano de usuário atualizado com sucesso");
    }

    @Operation(summary = "Excluir um plano de usuário", description = "Exclui um plano de usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano de usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirPlanoUsuario(@PathVariable Long id) {
        planoUsuarioService.excluirPlanoUsuario(id);
        return ResponseEntity.ok("Plano de usuário excluído com sucesso");
    }
}
