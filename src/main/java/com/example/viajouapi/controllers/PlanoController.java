package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Plano;
import com.example.viajouapi.services.PlanoService;
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
@RequestMapping("/viajouapi/planos")
public class PlanoController {
    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    // Buscando todos os planos
    @GetMapping("/buscar")
    public List<Plano> buscarPlanos() {
        return planoService.buscarPlanos();
    }

    // Inserindo um plano
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirPlano(@Valid @RequestBody Plano plano, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            planoService.salvarPlano(plano);
            return ResponseEntity.ok("Plano inserido com sucesso");
        }
    }

    // Atualizando uma parte do plano
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Plano planoExistente = planoService.buscarPlanoPorID(id);
        if (planoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nome":
                    planoExistente.setNome((String) valor);
                    break;
                case "descricao":
                    planoExistente.setDescricao((String) valor);
                    break;
                case "livrePropaganda":
                    planoExistente.setLivrePropaganda((Boolean) valor);
                    break;
                case "valor":
                    planoExistente.setValor((BigDecimal) valor);
                    break;
                case "duracao":
                    planoExistente.setDuracao((String) valor);
                    break;
                case "dataDesativacao":
                    planoExistente.setDataDesativacao((LocalDateTime) valor);
                    break;
            }
        });

        planoService.salvarPlano(planoExistente);
        return ResponseEntity.ok("Plano atualizado com sucesso");
    }
}
