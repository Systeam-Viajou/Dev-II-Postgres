package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.Usuario;
import com.example.viajouapi.services.AtracaoService;
import com.example.viajouapi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/atracao")
public class AtracaoController {
    private final AtracaoService atracaoService;

    public AtracaoController(AtracaoService atracaoService){
        this.atracaoService = atracaoService;
    }

    // Buscando todas as atrações
    @GetMapping("/buscar")
    public List<Atracao> buscarAtracoes(){
        return atracaoService.buscarAtracoes();
    }

    // Buscando a atração pelo nome, ele pode estar em qualquer parte do nome
    @GetMapping("/buscarPorNome/{nome}")
    public List<Atracao> buscarPorNome(@PathVariable String nome){
        return atracaoService.buscarAtracaoPorNome(nome);
    }

    // Inserindo uma atração
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirAtracao(@Valid @RequestBody Atracao atracao, BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            atracaoService.salvarAtracao(atracao);
            return ResponseEntity.ok("Atração inserido com sucesso");
        }
    }

    // Atrualizando uma parte da atração
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
                    atracaoExistente.setDataDesativacao((LocalDateTime) valor);
                    break;
            }
        });

        atracaoService.salvarAtracao(atracaoExistente);
        return ResponseEntity.ok("Atração atualizada com sucesso");
    }
}
