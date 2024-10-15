//package com.example.viajouapi.controllers;
//
//import com.example.viajouapi.models.Atracao;
//import com.example.viajouapi.models.Empresa;
//import com.example.viajouapi.models.Excursao;
//import com.example.viajouapi.models.PlanoUsuario;
//import com.example.viajouapi.services.PlanoUsuarioService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/viajouapi/plano-usuario")
//public class PlanoUsuarioController {
//    private final PlanoUsuarioService planoUsuarioService;
//
//    public PlanoUsuarioController(PlanoUsuarioService planoUsuarioService) {
//        this.planoUsuarioService = planoUsuarioService;
//    }
//
//    // Buscando todos os planos de usuários
//    @GetMapping("/buscar")
//    public List<PlanoUsuario> buscarPlanosUsuarios() {
//        return planoUsuarioService.buscarPlanosUsuarios();
//    }
//
//    @PatchMapping("/atualizarParcial/{uid}")
//    public ResponseEntity<String> atualizarParcial(
//            @PathVariable String uid,
//            @RequestBody Map<String, Object> atualizacoes) {
//
//        PlanoUsuario planoUsuarioExistente = planoUsuarioService.buscarPlanoUsuarioPorUsuarioId(uid);
//        if (planoUsuarioExistente == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano Usuario não encontrada");
//        }
//
//        atualizacoes.forEach((campo, valor) -> {
//            switch (campo) {
//                case "dataPagamento":
//                    planoUsuarioExistente.setDataPagamento((ZonedDateTime) valor);
//                    break;
//                case "dataTermino":
//                    planoUsuarioExistente.setDataTermino((ZonedDateTime) valor);
//                    break;
//            }
//        });
//
//        planoUsuarioService.salvarPlanoUsuario(planoUsuarioExistente);
//        return ResponseEntity.ok("Plano Usuario atualizada com sucesso");
//    }
//
//    // Inserindo um plano de usuário
//    @PostMapping("/inserir")
//    public ResponseEntity<String> inserirPlanoUsuario(@Valid @RequestBody PlanoUsuario planoUsuario, BindingResult resultado) {
//        if (resultado.hasErrors()) {
//            Map<String, String> erros = new HashMap<>();
//            for (FieldError erro : resultado.getFieldErrors()) {
//                erros.put(erro.getField(), erro.getDefaultMessage());
//            }
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
//        } else {
//            planoUsuarioService.salvarPlanoUsuario(planoUsuario);
//            return ResponseEntity.ok("Plano de usuário inserido com sucesso");
//        }
//    }
//}
