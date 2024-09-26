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

    @GetMapping("/buscar")
    public List<Usuario> buscarUsuarios(){
        return usuarioService.buscarUsuarios();
    }

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

    @PutMapping("/atualizar/{uid}")
    public ResponseEntity<String> atualizarUsuario( @PathVariable String uid, @Valid @RequestBody Usuario usuarioAtualizado,  BindingResult resultado){
        if(resultado.hasErrors()){
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : resultado.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toString());
        }
        else{
            Usuario usuarioExistence = usuarioService.buscarPorUID(uid);
            Usuario usuario = usuarioExistence;
            usuario.setUid(usuarioAtualizado.getUid());
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setSobrenome(usuarioAtualizado.getSobrenome());
            usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
            usuario.setUsername(usuarioAtualizado.getUsername());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setTelefone(usuarioAtualizado.getTelefone());
            usuario.setGenero(usuarioAtualizado.getGenero());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok("Usuario atualizado com sucesso");
        }
    }

    @DeleteMapping("/excluir/{uid}")
    public ResponseEntity<String> excluirUsuario(@PathVariable String uid)  {
        if(usuarioService.buscarPorUID(uid) != null) {
            usuarioService.excluirUsuario(uid);
            return ResponseEntity.ok("Usuario excluido com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
    }

    @GetMapping("/buscar/username/{username}")
    public Usuario buscarPorUsername(@PathVariable String username){
        return usuarioService.buscarPorUsername(username);
    }

    @GetMapping("/buscar/email/{email}")
    public Usuario buscarPorEmail(@PathVariable String email){
        return usuarioService.buscarPorEmail(email);
    }
}