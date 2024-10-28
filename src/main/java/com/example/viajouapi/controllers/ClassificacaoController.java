package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Classificacao;
import com.example.viajouapi.services.ClassificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/viajouapi/classificacoes")
public class ClassificacaoController {

    private final ClassificacaoService classificacaoService;

    public ClassificacaoController(ClassificacaoService classificacaoService) {
        this.classificacaoService = classificacaoService;
    }

    // Buscando todas as classificações
    @Operation(summary = "Buscar todas as classificações", description = "Busca todas as classificações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classificações retornadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<Classificacao> buscarTodasClassificacoes() {
        return classificacaoService.buscarClassificacao();
    }

    // Buscando classificação por ID
    @Operation(summary = "Buscar classificação por ID", description = "Busca uma classificação específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classificação retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Classificação não encontrada!")
    })
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Classificacao> buscarPorId(@PathVariable Long id) {
        try {
            Classificacao classificacao = classificacaoService.buscarClassificacaoPorID(id);
            return ResponseEntity.ok(classificacao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Buscando classificação por usuário (UID)
    @Operation(summary = "Buscar classificação por usuário", description = "Busca uma classificação específica pelo UID do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classificação retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Classificação não encontrada!")
    })
    @GetMapping("/buscarPorUsuario/{uid}")
    public ResponseEntity<Classificacao> buscarPorUsuario(@PathVariable String uid) {
        try {
            Classificacao classificacao = classificacaoService.buscarClassificacaoPorUsuario(uid);
            return ResponseEntity.ok(classificacao);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Inserindo uma classificação
    @Operation(summary = "Inserir uma classificação", description = "Insere uma nova classificação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classificação inserida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parâmetro está incorreto!")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirClassificacao(@Valid @RequestBody Classificacao classificacao, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            classificacaoService.salvarClassificacao(classificacao);
            return ResponseEntity.ok("Classificação inserida com sucesso");
        }
    }

    // Atualizando parcialmente uma classificação
    @Operation(summary = "Atualizar parcialmente uma classificação", description = "Atualiza parcialmente as informações de uma classificação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classificação atualizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parâmetro está incorreto!"),
            @ApiResponse(responseCode = "404", description = "Classificação não encontrada")
    })
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        try {
            Classificacao classificacaoExistente = classificacaoService.buscarClassificacaoPorID(id);

            atualizacoes.forEach((campo, valor) -> {
                switch (campo) {
                    case "nota":
                        classificacaoExistente.setNota(((Number) valor).floatValue());
                        break;
                    // Adicione outros campos que podem ser atualizados, se necessário
                }
            });

            classificacaoService.salvarClassificacao(classificacaoExistente);
            return ResponseEntity.ok("Classificação atualizada com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação não encontrada");
        }
    }

    @Operation(summary = "Inserir uma classificação (Pocedure)", description = "Insere uma nova classificação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classificação inserida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parâmetro está incorreto!")
    })
    @PostMapping("/avaliar-Atracao/{uid}/{nota}/{atracao}")
    public void avaliarAtracao(
            @PathVariable("uid") String uidUsuario,
            @PathVariable("nota") Float nota,
            @PathVariable("atracao") Integer idAtracao) {

        classificacaoService.avaliarAtracao(nota, uidUsuario, idAtracao);
    }


}
