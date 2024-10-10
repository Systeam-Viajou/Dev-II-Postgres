package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Classificacao;
import com.example.viajouapi.models.Evento;
import com.example.viajouapi.models.Usuario;
import com.example.viajouapi.services.ClassificacaoService;
import com.example.viajouapi.services.EventoService;
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
@RequestMapping("/viajouapi/classificacoes")
public class ClassificacaoController {
    private final ClassificacaoService classificacaoService;

    public ClassificacaoController(ClassificacaoService classificacaoService){
        this.classificacaoService = classificacaoService;
    }

    // Buscando todos os eventos
    @GetMapping("/buscar")
    public List<Classificacao> buscarClassificacao(){
        return classificacaoService.buscarClassificacao();
    }

    // Inserindo um evento
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirClassificacao(@Valid @RequestBody Classificacao classificacao, BindingResult resultado) {
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            classificacaoService.salvarClassificacao(classificacao);
            return ResponseEntity.ok("Classificação inserido com sucesso");
        }
    }

    // Atualizando uma parte do ponto turistico
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        Classificacao classificacaoExistente = classificacaoService.buscarClassificacaoPorID(id);
        if (classificacaoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classificação não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nota":
                    classificacaoExistente.setNota((Float) valor);
                    break;
                case "idUsuario":
                    classificacaoExistente.setIdUsuario((Usuario) valor);
                    break;
                case "atracao":
                    classificacaoExistente.setAtracao((Atracao) valor);
                    break;
            }
        });

        classificacaoService.salvarClassificacao(classificacaoExistente);
        return ResponseEntity.ok("Classificação atualizado com sucesso");
    }
}
