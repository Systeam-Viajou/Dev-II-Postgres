package com.example.viajouapi.services;

import com.example.viajouapi.models.PlanoUsuario;
import com.example.viajouapi.repositorys.PlanoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoUsuarioService {
    private final PlanoUsuarioRepository planoUsuarioRepository;

    public PlanoUsuarioService(PlanoUsuarioRepository planoUsuarioRepository) {
        this.planoUsuarioRepository = planoUsuarioRepository;
    }

    // Buscando todos os planos de usuários
    public List<PlanoUsuario> buscarPlanosUsuarios() {
        return planoUsuarioRepository.findAll();
    }

    // Buscando plano de usuário pelo ID
    public PlanoUsuario buscarPlanoUsuarioPorID(Long planoId, String usuarioId) {
        return planoUsuarioRepository.findByPlanoIdAndUsuarioId(planoId, usuarioId).orElseThrow(() ->
                new RuntimeException("Plano de usuário não encontrado"));
    }

    // Salvando um plano de usuário
    public PlanoUsuario salvarPlanoUsuario(PlanoUsuario planoUsuario) {
        return planoUsuarioRepository.save(planoUsuario);
    }
}
