package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Empresa;
import com.example.viajouapi.models.Evento;
import com.example.viajouapi.models.Excursao;
import com.example.viajouapi.services.EventoService;
import com.example.viajouapi.services.ExcursaoService;
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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/excursoes")
public class ExcursaoController {
    private final ExcursaoService excursaoService;

    public ExcursaoController(ExcursaoService excursaoService){
        this.excursaoService = excursaoService;
    }

    // Buscando todos os eventos
    @Operation(summary = "Buscar todas as Excursões", description = "Retorna uma lista de todas as Excursões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excursões recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<Excursao> buscarExcursao(){
        return excursaoService.buscarExcursao();
    }

    @Operation(summary = "Buscar excursões paginados", description = "Retorna uma lista de excursões com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excursões recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar/paginado")
    public List<Excursao> buscarExcursaoPaginado(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return excursaoService.buscarExcursaoPaginacao(offset, limit);
    }

    // Inserindo um evento
    @Operation(summary = "Inserir uma nova Excursão", description = "Insere uma nova Excursão no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excursão inserido com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parametro está incorreto!")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirEvento(@Valid @RequestBody Excursao excursao, BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            // Converter dataInicio e dataTermino para o fuso horário de Brasília
            if (excursao.getDataInicio() != null) {
                excursao.setDataInicio(excursao.getDataInicio().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
            }
            if (excursao.getDataTermino() != null) {
                excursao.setDataTermino(excursao.getDataTermino().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
            }
            if (excursao.getDataDesativacao() != null) {
                excursao.setDataDesativacao(excursao.getDataDesativacao().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
            }

            excursaoService.salvarExcursao(excursao);
            return ResponseEntity.ok("Excursão inserida com sucesso");
        }
    }


    @Operation(summary = "Buscar Excursão por id", description = "Busca uma Excursão pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercursão recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "404", description = "Exercursão não encontrado")
    })
    @GetMapping("/buscarPorId/{id}")
    public Excursao buscarPorId(Long id){
        return excursaoService.buscarExcursaoPorID(id);
    }

    // Atualizando uma parte do ponto turistico
    @Operation(summary = "Atualizar parcialmente uma Excursão", description = "Atualiza parcialmente as informações de uma Excursão pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excursão atualizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "400", description = "Algum parametro está incorreto!"),
            @ApiResponse(responseCode = "404", description = "Excursão não encontrada")
    })
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Excursao excursaoExistente = excursaoService.buscarExcursaoPorID(id);
        if (excursaoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Excursão não encontrada");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "capacidade":
                    excursaoExistente.setCapacidade((String) valor);
                    break;
                case "quantidadePessoas":
                    excursaoExistente.setQuantidadePessoas((String) valor);
                    break;
                case "precoTotal":
                    excursaoExistente.setPrecoTotal(new BigDecimal((String) valor));
                    break;
                case "dataInicio":
                    ZonedDateTime zonedDateTimeInicio = ZonedDateTime.parse((String) valor).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
                    excursaoExistente.setDataInicio(ZonedDateTime.from(zonedDateTimeInicio.toInstant()));
                    break;
                case "dataTermino":
                    ZonedDateTime zonedDateTimeTermino = ZonedDateTime.parse((String) valor).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
                    excursaoExistente.setDataTermino(ZonedDateTime.from(zonedDateTimeTermino.toInstant()));
                    break;
                case "atracao":
                    excursaoExistente.setAtracao((Atracao) valor);
                    break;
                case "empresa":
                    excursaoExistente.setEmpresa((Empresa) valor);
                    break;
                case "dataDesativacao":
                    ZonedDateTime zonedDateTimedeactivation = ZonedDateTime.parse((String) valor).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
                    excursaoExistente.setDataDesativacao(ZonedDateTime.from(zonedDateTimedeactivation.toInstant()));
                    break;
            }
        });

        excursaoService.salvarExcursao(excursaoExistente);
        return ResponseEntity.ok("Excursão atualizada com sucesso");
    }
}
