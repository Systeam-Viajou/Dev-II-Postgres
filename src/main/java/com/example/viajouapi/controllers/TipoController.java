package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Tipo;
import com.example.viajouapi.services.TipoService;
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
@RequestMapping("/viajouapi/tipos")
public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    // Buscando todos os tipos
    @Operation(summary = "Buscar todos os tipos", description = "Busca todos os tipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<Tipo> buscarTodosTipos() {
        return tipoService.buscarTodosTipos();
    }

    // Buscando tipos pelo nome
    @Operation(summary = "Buscar tipos por nome", description = "Busca todos os tipos com este nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "404", description = "Tipo não encontrado!")
    })
    @GetMapping("/buscarPorNome/{nome}")
    public List<Tipo> buscarPorNome(@PathVariable String nome) {
        return tipoService.buscarTipoPorNome(nome);
    }

    // Inserindo um tipo
    @Operation(summary = "Inserir um tipo", description = "Insere um tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo inserido com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parâmetro está incorreto!")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirTipo(@Valid @RequestBody Tipo tipo, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            tipoService.salvarTipo(tipo);
            return ResponseEntity.ok("Tipo inserido com sucesso");
        }
    }

    // Atualizando parcialmente um tipo
    @Operation(summary = "Atualizar parcialmente um tipo", description = "Atualiza parcialmente as informações de um tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parâmetro está incorreto!"),
            @ApiResponse(responseCode = "404", description = "Tipo não encontrado")
    })
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Tipo tipoExistente = tipoService.buscarTipoPorId(id);
        if (tipoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nome":
                    tipoExistente.setNome((String) valor);
                    break;
            }
        });

        tipoService.salvarTipo(tipoExistente);
        return ResponseEntity.ok("Tipo atualizado com sucesso");
    }
}
