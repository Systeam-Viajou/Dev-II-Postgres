package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Evento;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.services.EventoService;
import com.example.viajouapi.services.PontoTuristicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    // Buscando todos os eventos
    @GetMapping("/buscar")
    public List<Evento> buscarEvento(){
        return eventoService.buscarEvento();
    }

    // Inserindo um evento
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirEvento(@Valid @RequestBody Evento evento, BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            // Converter dataInicio e dataTermino para o fuso horário de Brasília
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

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirEvento(@PathVariable long id) {

        if (eventoService.buscarEventoPorID(id) != null) {
            eventoService.excluirEvento(id);
            return ResponseEntity.ok("Evento excluido com sucesso");

        } else {
            return ResponseEntity.ok("Evento não existe");
        }
    }

    // Atualizando uma parte do ponto turistico
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
