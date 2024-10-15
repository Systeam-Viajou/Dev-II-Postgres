package com.example.viajouapi.controllers;

import com.example.viajouapi.models.PesquisaPerfil;
import com.example.viajouapi.services.PesquisaPerfilService;
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
@RequestMapping("/viajouapi/pesquisas")
public class PesquisaPerfilController {
    private final PesquisaPerfilService pesquisaPerfilService;

    public PesquisaPerfilController(PesquisaPerfilService pesquisaPerfilService) {
        this.pesquisaPerfilService = pesquisaPerfilService;
    }

    // Buscar todas as pesquisas de perfil
    @GetMapping("/buscar")
    public List<PesquisaPerfil> buscarPesquisas() {
        return pesquisaPerfilService.buscarPesquisas();
    }

    // Buscar pesquisa de perfil por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PesquisaPerfil> buscarPesquisaPorId(@PathVariable Long id) {
        PesquisaPerfil pesquisa = pesquisaPerfilService.buscarPesquisaPorId(id);
        return ResponseEntity.ok(pesquisa);
    }

    // Buscar pesquisas de perfil por UID do usuário
    @GetMapping("/buscar/usuario/{uid}")
    public List<PesquisaPerfil> buscarPesquisasPorUidUsuario(@PathVariable String uid) {
        return pesquisaPerfilService.buscarPesquisasPorUidUsuario(uid);
    }

    // Inserir uma nova resposta de pesquisa de perfil
    @PostMapping("/inserir")
    public ResponseEntity<?> inserirPesquisa(@Valid @RequestBody PesquisaPerfil pesquisaPerfil, BindingResult resultado) {
        if (resultado.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
        }

        pesquisaPerfilService.salvarPesquisa(pesquisaPerfil);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pesquisa inserida com sucesso");
    }
}
