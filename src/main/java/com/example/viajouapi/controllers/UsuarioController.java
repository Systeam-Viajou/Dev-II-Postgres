package com.example.viajouapi.controllers;

import com.example.viajouapi.models.Usuario;
import com.example.viajouapi.repositorys.UsuarioRepository;
import com.example.viajouapi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viajouapi/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final Validator validador;

    public UsuarioController(UsuarioService usuarioService, Validator validator){
        this.usuarioService = usuarioService;
        this.validador = validator;
    }

    // Buscanco todos os usuarios
    @GetMapping("/buscar")
    public List<Usuario> buscarUsuarios(){
        return usuarioService.buscarUsuarios();
    }

    @GetMapping("/buscar/username/{username}")
    public Usuario buscarPorUsername(@PathVariable String username){
        return usuarioService.buscarPorUsername(username);
    }

    @GetMapping("/buscar/email/{email}")
    public Usuario buscarPorEmail(@PathVariable String email){
        return usuarioService.buscarPorEmail(email);
    }

    // Inserindo um usuario
    @PostMapping("/inserir")
    public ResponseEntity<String> inserirUsuario(@Valid @RequestBody Usuario usuario, BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();

            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok("Usuario inserido com sucesso");
        }
    }

    // Atrualizando uma parte do usuario
    @PatchMapping("/atualizarParcial/{uid}")
    public ResponseEntity<String> atualizarParcial(
            @PathVariable String uid,
            @RequestBody Map<String, Object> atualizacoes) {

        Usuario usuarioExistente = usuarioService.buscarPorUID(uid);
        if (usuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        atualizacoes.forEach((campo, valor) -> {
            switch (campo) {
                case "nome":
                    usuarioExistente.setNome((String) valor);
                    break;
                case "sobrenome":
                    usuarioExistente.setSobrenome((String) valor);
                    break;
                case "dataNascimento":
                    usuarioExistente.setDataNascimento((Date) valor);
                    break;
                case "username":
                    usuarioExistente.setUsername((String) valor);
                    break;
                case "email":
                    usuarioExistente.setEmail((String) valor);
                    break;
                case "telefone":
                    usuarioExistente.setTelefone((String) valor);
                    break;
                case "genero":
                    usuarioExistente.setGenero((String) valor);
                    break;
                case "senha":
                    usuarioExistente.setSenha((String) valor);
                    break;
                case "cpf":
                    usuarioExistente.setCpf((String) valor);
                    break;
                // Adicione outros campos conforme necessário
            }
        });

        usuarioService.salvarUsuario(usuarioExistente);
        return ResponseEntity.ok("Usuário atualizado parcialmente com sucesso");
    }

}