package com.example.viajouapi.controllers;

import com.example.viajouapi.models.PlanoUsuario;
import com.example.viajouapi.services.PlanoUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/plano-usuario")
public class PlanoUsuarioController {
    private final PlanoUsuarioService planoUsuarioService;

    public PlanoUsuarioController(PlanoUsuarioService planoUsuarioService) {
        this.planoUsuarioService = planoUsuarioService;
    }

    // Buscando todos os planos de usuários
    @GetMapping("/buscar")
    public List<PlanoUsuario> buscarPlanosUsuarios() {
        return planoUsuarioService.buscarPlanosUsuarios();
    }

    // Buscando um plano de usuário pelo ID
    @GetMapping("/buscar/{planoId}/{usuarioId}")
    public ResponseEntity<PlanoUsuario> buscarPlanoUsuarioPorID(@PathVariable Long planoId, @PathVariable String usuarioId) {
        PlanoUsuario planoUsuario = planoUsuarioService.buscarPlanoUsuarioPorID(planoId, usuarioId);
        return ResponseEntity.ok(planoUsuario);
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
}
