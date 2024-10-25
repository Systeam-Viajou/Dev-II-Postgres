package com.example.viajouapi.services;

import com.example.viajouapi.models.Usuario;
import com.example.viajouapi.repositorys.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundException("O e-mail não existe"));
    }

    public Usuario buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username).
                orElseThrow(() -> new EntityNotFoundException("O username não existe"));

    }
    public Usuario buscarPorUID(String uid){
        return usuarioRepository.findByUid(uid).
                orElseThrow(() -> new EntityNotFoundException("O UID não existe"));
    }

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
}
