package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Evento;
import com.example.viajouapi.services.EventoService;
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
@RequestMapping("/viajouapi/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Operation(summary = "Buscar todos os eventos", description = "Retorna uma lista de todos os eventos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar")
    public List<Evento> buscarEvento() {
        return eventoService.buscarEvento();
    }

    @Operation(summary = "Buscar Evento por atracao", description = "Busca um Evento por atração")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento recuperado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @GetMapping("/buscar/atracao/{id}")
    public Evento buscarPorAtracao(@PathVariable Long id){
        return eventoService.buscarPorAtracao(id);
    }

    @Operation(summary = "Buscar eventos paginados", description = "Retorna uma lista de eventos com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/buscar/paginado")
    public List<Evento> buscarEventoPaginado(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        return eventoService.buscarEventoPaginacao(offset, limit);
    }

    @Operation(summary = "Inserir um novo evento", description = "Insere um novo evento no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirEvento(@Valid @RequestBody Evento evento, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            if (evento.getDataInicio() != null) {
                evento.setDataInicio(evento.getDataInicio().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
            }
            if (evento.getDataTermino() != null) {
                evento.setDataTermino(evento.getDataTermino().withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
            }

            eventoService.salvarEvento(evento);
            return ResponseEntity.ok("Evento inserido com sucesso");
        }
    }

    @Operation(summary = "Atualizar parcialmente um evento", description = "Atualiza parcialmente as informações de um evento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Evento eventoExistente = eventoService.buscarEventoPorID(id);
        if (eventoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "dataInicio":
                    ZonedDateTime zonedDateTimeInicio = ZonedDateTime.parse((String) valor).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
                    eventoExistente.setDataInicio(ZonedDateTime.from(zonedDateTimeInicio.toInstant()));
                    break;
                case "dataTermino":
                    ZonedDateTime zonedDateTimeTermino = ZonedDateTime.parse((String) valor).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
                    eventoExistente.setDataTermino(ZonedDateTime.from(zonedDateTimeTermino.toInstant()));
                    break;
                case "precoPessoa":
                    eventoExistente.setPrecoPessoa(new BigDecimal((String) valor));
                    break;
                case "atracao":
                    eventoExistente.setAtracao((Atracao) valor);
                    break;
                case "dataDesativacao":
                    eventoExistente.setDataDesativacao(ZonedDateTime.parse((String) valor));
                    break;
            }
        });

        eventoService.salvarEvento(eventoExistente);
        return ResponseEntity.ok("Evento atualizado com sucesso");
    }
}
