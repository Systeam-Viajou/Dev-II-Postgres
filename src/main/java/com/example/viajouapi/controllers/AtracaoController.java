package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.Tipo;
import com.example.viajouapi.services.AtracaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/atracoes")
public class AtracaoController {

    private final AtracaoService atracaoService;

    public AtracaoController(AtracaoService atracaoService) {
        this.atracaoService = atracaoService;
    }

    // Buscando todas as atrações
    @Operation(summary = "Buscar todas as atrações", description = "Busca todas as atrações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atrações retornadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<Atracao> buscarTodasAtracoes() {
        return atracaoService.buscarAtracoes();
    }

    // Buscando atração por ID
    @Operation(summary = "Buscar atração por ID", description = "Busca uma atração específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atração retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "404", description = "Atração não encontrada!")
    })
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Atracao> buscarPorId(@PathVariable Long id) {
        Atracao atracao = atracaoService.buscarAtracaoPorID(id);
        if (atracao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(atracao);
    }

    @Operation(summary = "Gerar 15 atrações aleatórias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atrações aleatórias geradas com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Atracao.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @GetMapping("/aleatorias")
    public ResponseEntity<?> gerarAtracoesAleatorias() {
        try {
            List<Atracao> atracoesAleatorias = atracaoService.gerarAtracoesAleatorias();
            return ResponseEntity.ok(atracoesAleatorias);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }


    // Inserindo uma atração
    @Operation(summary = "Inserir uma atração", description = "Insere uma nova atração")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atração inserida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parâmetro está incorreto!")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirAtracao(@Valid @RequestBody Atracao atracao, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            atracaoService.salvarAtracao(atracao);
            return ResponseEntity.ok("Atração inserida com sucesso");
        }
    }

    // Atualizando parcialmente uma atração
    @Operation(summary = "Atualizar parcialmente uma atração", description = "Atualiza parcialmente as informações de uma atração")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atração atualizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parâmetro está incorreto!"),
            @ApiResponse(responseCode = "404", description = "Atração não encontrada")})
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Atracao atracaoExistente = atracaoService.buscarAtracaoPorID(id);
        if (atracaoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atração não encontrada");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nome":
                    atracaoExistente.setNome((String) valor);
                    break;
                case "descricao":
                    atracaoExistente.setDescricao((String) valor);
                    break;
                case "endereco":
                    atracaoExistente.setEndereco((String) valor);
                    break;
                case "acessibilidade":
                    atracaoExistente.setAcessibilidade((Boolean) valor);
                    break;
                case "mediaClassificacao":
                    atracaoExistente.setMediaClassificacao((BigDecimal) valor);
                    break;
                case "categoria":
                    atracaoExistente.setCategoria((Categoria) valor);
                    break;
                case "dataDesativacao":
                    atracaoExistente.setDataDesativacao(ZonedDateTime.parse((String) valor));
                    break;
                case "tipo":
                    // Conversão manual para o objeto Tipo
                    Map<String, Object> tipoData = (Map<String, Object>) valor;
                    Long tipoId = ((Number) tipoData.get("id")).longValue();
                    Tipo tipo = new Tipo();
                    tipo.setId(tipoId);
                    atracaoExistente.setTipo(tipo);
                    break;
            }
        });

        atracaoService.salvarAtracao(atracaoExistente);
        return ResponseEntity.ok("Atração atualizada com sucesso");
    }
}
