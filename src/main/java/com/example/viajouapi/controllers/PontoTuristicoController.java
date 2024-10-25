package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.repositorys.PontoTuristicoReporitory;
import com.example.viajouapi.services.PontoTuristicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/pontosturisticos")
public class PontoTuristicoController {
    private final PontoTuristicoService pontoTuristicoService;

    public PontoTuristicoController(PontoTuristicoService pontoTuristicoService){
        this.pontoTuristicoService = pontoTuristicoService;
    }

    // Buscando todos os pontos turisticos
    @Operation(summary = "Buscar Todos os pontos turisticos", description = "Busca todos os pontos turisticos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PontosTuristicos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<PontoTuristico> buscarPontoTuristico(){
        return pontoTuristicoService.buscarPontoTuristico();
    }

    // Inserindo um ponto turistico
    @Operation(summary = "Inserir um ponto turistico", description = "Insere um ponto turistico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PontosTuristico inserido com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parametro está incorreto!")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirPontoTuristico(@Valid @RequestBody PontoTuristico pontoTuristico, BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            pontoTuristicoService.salvarPontoTuristico(pontoTuristico);
            return ResponseEntity.ok("Ponto turístico inserido com sucesso");
        }
    }

    @Operation(summary = "Buscar Ponto Turistico por atracao", description = "Busca um Ponto turistico por atração")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ponto turistico recuperado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "404", description = "Ponto turistico não encontrado")
    })
    @GetMapping("/buscar/atracao/{id}")
    public PontoTuristico buscarPorAtracao(@PathVariable Long id){
        return pontoTuristicoService.buscarPontoTuristicoPorAtracao(id);
    }

    // Atualizando uma parte do ponto turistico
    @Operation(summary = "Atualizar parcialmente um Ponto Turistico", description = "Atualiza parcialmente as informações de um Ponto turistico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ponto Turistico atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parametro está incorreto!"),
            @ApiResponse(responseCode = "404", description = "Ponto Turistico não encontrado")
    })
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        PontoTuristico pontoExistente = pontoTuristicoService.buscarPontoPorID(id);
        if (pontoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ponto turístico não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "atracao":
                    pontoExistente.setAtracao((Atracao) valor);
                    break;
                case "dataDesativacao":
                    pontoExistente.setDataDesativacao(ZonedDateTime.parse((String) valor));
                    break;
            }
        });

        pontoTuristicoService.salvarPontoTuristico(pontoExistente);
        return ResponseEntity.ok("Ponto turístico atualizado com sucesso");
    }
}
