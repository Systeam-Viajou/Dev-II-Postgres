package com.example.viajouapi.services;

import com.example.viajouapi.models.Usuario;
import com.example.viajouapi.repositorys.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // Buscando todos os usuarios
    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }

    // Buscanco usuario pelo email
    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    // Buscando usuario pelo username
    public Usuario buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    // Buscando o usuario pelo uid
    public Usuario buscarPorUID(String uid){
        return usuarioRepository.findByUid(uid);
    }

    // Salvando e atualizando o usuario
    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
