package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.PontoTuristico;
import com.example.viajouapi.services.PontoTuristicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/pontoturistico")
public class PontoTuristicoController {
    private final PontoTuristicoService pontoTuristicoService;

    public PontoTuristicoController(PontoTuristicoService pontoTuristicoService){
        this.pontoTuristicoService = pontoTuristicoService;
    }

    @GetMapping("/buscar")
    public List<PontoTuristico> buscarAtracoes(){
        return pontoTuristicoService.buscarPontoTuristico();
    }

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
            pontoTuristicoService.salvarPOntoTuristico(pontoTuristico);
            return ResponseEntity.ok("Pontoturístico inserido com sucesso");
        }
    }

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
                    pontoExistente.setDataDesativacao((LocalDateTime) valor);
                    break;
            }
        });

        pontoTuristicoService.salvarPOntoTuristico(pontoExistente);
        return ResponseEntity.ok("Ponto turístico atualizado com sucesso");
    }
}
