//package com.example.viajouapi.controllers;
//
//import com.example.viajouapi.models.UsuarioFigurinha;
//import com.example.viajouapi.services.UsuarioFigurinhaService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/viajouapi/figurinha")
//public class UsuarioFigurinhaController {
//    private final UsuarioFigurinhaService usuarioFigurinhaService;
//
//    public UsuarioFigurinhaController(UsuarioFigurinhaService usuarioFigurinhaService) {
//        this.usuarioFigurinhaService = usuarioFigurinhaService;
//    }
//
//    // Buscando todas as figurinhas
//    @GetMapping("/buscar")
//    public List<UsuarioFigurinha> buscarFigurinhas() {
//        return usuarioFigurinhaService.buscarFigurinhas();
//    }
//
//    // Inserindo uma figurinha
//    @PostMapping("/inserir")
//    public ResponseEntity<String> inserirFigurinha(@Valid @RequestBody UsuarioFigurinha usuarioFigurinha, BindingResult resultado) {
//        if (resultado.hasErrors()) {
//            Map<String, String> erros = new HashMap<>();
//
//            for (FieldError erro : resultado.getFieldErrors()) {
//                erros.put(erro.getField(), erro.getDefaultMessage());
//            }
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
//        } else {
//            usuarioFigurinhaService.salvarFigurinha(usuarioFigurinha);
//            return ResponseEntity.ok("Figurinha inserida com sucesso");
//        }
//    }
//
//    // Excluindo uma figurinha
//    @DeleteMapping("/excluir/{id}")
//    public ResponseEntity<String> excluirFigurinha(@PathVariable Long id) {
//        UsuarioFigurinha figurinhaExistente = usuarioFigurinhaService.buscarFigurinhaPorID(id);
//        if (figurinhaExistente != null) {
//            usuarioFigurinhaService.salvarFigurinha(figurinhaExistente); // Aqui poderia ser uma chamada para excluir, mas não foi incluído no seu exemplo
//            return ResponseEntity.ok("Figurinha excluída com sucesso");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Figurinha não existe");
//        }
//    }
//
//    // Buscando figurinhas pelo ID do usuário
//    @GetMapping("/usuario/{idUsuario}")
//    public List<UsuarioFigurinha> buscarFigurinhasPorUsuario(@PathVariable String idUsuario) {
//        return usuarioFigurinhaService.buscarFigurinhasPorUsuario(idUsuario);
//    }
//}
