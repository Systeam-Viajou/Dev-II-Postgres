package com.example.viajouapi.controllers;

import com.example.viajouapi.models.UsuarioFigurinha;
import com.example.viajouapi.services.UsuarioFigurinhaService;
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
@RequestMapping("/viajouapi/usuario-figurinhas")
public class UsuarioFigurinhaController {
    private final UsuarioFigurinhaService usuarioFigurinhaService;

    public UsuarioFigurinhaController(UsuarioFigurinhaService usuarioFigurinhaService) {
        this.usuarioFigurinhaService = usuarioFigurinhaService;
    }

    @Operation(summary = "Buscar todas as figurinhas dos usuários", description = "Retorna uma lista de todas as figurinhas de usuários cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Figurinhas dos usuários recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<UsuarioFigurinha> buscarFigurinhas() {
        return usuarioFigurinhaService.buscarFigurinhas();
    }

    @Operation(summary = "Buscar figurinha por ID", description = "Retorna uma figurinha de usuário específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Figurinha encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Figurinha não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioFigurinha> buscarFigurinhaPorID(@PathVariable Long id) {
        UsuarioFigurinha figurinha = usuarioFigurinhaService.buscarFigurinhaPorID(id);
        return ResponseEntity.ok(figurinha);
    }

    @Operation(summary = "Buscar figurinhas de um usuário", description = "Retorna todas as figurinhas associadas a um usuário específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Figurinhas do usuário recuperadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma figurinha encontrada para o usuário"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar-por-usuario/{idUsuario}")
    public ResponseEntity<List<UsuarioFigurinha>> buscarFigurinhasPorUsuario(@PathVariable String idUsuario) {
        List<UsuarioFigurinha> figurinhas = usuarioFigurinhaService.buscarFigurinhasPorUsuario(idUsuario);
        return ResponseEntity.ok(figurinhas);
    }

    @Operation(summary = "Inserir uma nova figurinha de usuário", description = "Insere uma nova figurinha de usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Figurinha inserida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirFigurinha(@Valid @RequestBody UsuarioFigurinha usuarioFigurinha, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            usuarioFigurinhaService.salvarFigurinha(usuarioFigurinha);
            return ResponseEntity.status(HttpStatus.CREATED).body("Figurinha inserida com sucesso");
        }
    }
}
