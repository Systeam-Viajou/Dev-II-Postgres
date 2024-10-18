package com.example.viajouapi.controllers;

import com.example.viajouapi.models.PlanoUsuario;
import com.example.viajouapi.services.PlanoUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/planos-usuario")
public class PlanoUsuarioController {
    private final PlanoUsuarioService planoUsuarioService;

    public PlanoUsuarioController(PlanoUsuarioService planoUsuarioService) {
        this.planoUsuarioService = planoUsuarioService;
    }

    // Buscando todos os planos de usuários
    @GetMapping("/buscar")
    public List<PlanoUsuario> buscarPlanosUsuarios() {
        return planoUsuarioService.buscarTodos();
    }

    // Buscando planos por UID do usuário
    @GetMapping("/buscar-por-usuario/{uid}")
    public List<PlanoUsuario> buscarPorUsuario(@PathVariable String uid) {
        return planoUsuarioService.buscarPorUsuarioUid(uid);
    }

    // Buscando planos ativos (com data de término posterior à data atual)
    @GetMapping("/ativos")
    public List<PlanoUsuario> buscarPlanosAtivos() {
        return planoUsuarioService.buscarPlanosAtivos();
    }

    // Inserindo um plano de usuário
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirPlanoUsuario(@Valid @RequestBody PlanoUsuario planoUsuario, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        } else {
            planoUsuarioService.salvarPlanoUsuario(planoUsuario);
            return ResponseEntity.ok("Plano de usuário inserido com sucesso");
        }
    }

    // Atualizando parcialmente um plano de usuário
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> atualizacoes) {

        PlanoUsuario planoUsuarioExistente = planoUsuarioService.buscarPorID(id);
        if (planoUsuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de usuário não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "dataPagamento":
                    planoUsuarioExistente.setDataPagamento((LocalDateTime) valor);
                    break;
                case "dataTermino":
                    planoUsuarioExistente.setDataTermino((LocalDateTime) valor);
                    break;
            }
        });

        planoUsuarioService.salvarPlanoUsuario(planoUsuarioExistente);
        return ResponseEntity.ok("Plano de usuário atualizado com sucesso");
    }

    // Excluir um plano de usuário
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirPlanoUsuario(@PathVariable Long id) {
        planoUsuarioService.excluirPlanoUsuario(id);
        return ResponseEntity.ok("Plano de usuário excluído com sucesso");
    }
}
