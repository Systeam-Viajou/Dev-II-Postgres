package com.example.viajouapi.services;

import com.example.viajouapi.models.Usuario;
import com.example.viajouapi.repositorys.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){this.usuarioRepository = usuarioRepository;}

    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorUID(String uid){
        return usuarioRepository.findByUid(uid);
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){return usuarioRepository.save(usuario);}

    public Usuario excluirUsuario(String uid){
        Usuario usuario = buscarPorUID(uid);
        usuarioRepository.delete(usuario);
        return usuario;
    }
}
