package com.example.viajouapi.services;

import com.example.viajouapi.models.PlanoUsuario;
import com.example.viajouapi.repositorys.PlanoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanoUsuarioService {

    private final PlanoUsuarioRepository planoUsuarioRepository;

    public PlanoUsuarioService(PlanoUsuarioRepository planoUsuarioRepository) {
        this.planoUsuarioRepository = planoUsuarioRepository;
    }

    // Buscar todos os registros de planos de usuários
    public List<PlanoUsuario> buscarTodos() {
        return planoUsuarioRepository.findAll();
    }

    // Buscar plano de um usuário específico
    public List<PlanoUsuario> buscarPorUsuarioUid(String uid) {
        return planoUsuarioRepository.findByUsuarioUid(uid);
    }

    // Buscar um plano específico de um usuário
    public List<PlanoUsuario> buscarPorPlanoEUsuario(Long planoId, String uid) {
        return planoUsuarioRepository.findByPlanoIdAndUsuarioUid(planoId, uid);
    }

    // Buscar todos os planos ativos (com data de término posterior à data atual)
    public List<PlanoUsuario> buscarPlanosAtivos() {
        LocalDateTime dataAtual = LocalDateTime.now();
        return planoUsuarioRepository.findByDataTerminoAfter(dataAtual);
    }

    // Salvar ou atualizar um plano de usuário
    public PlanoUsuario salvarPlanoUsuario(PlanoUsuario planoUsuario) {
        return planoUsuarioRepository.save(planoUsuario);
    }

    // Excluir um plano de usuário pelo ID
    public void excluirPlanoUsuario(Long id) {
        planoUsuarioRepository.deleteById(id);
    }

    // Buscar por ID
    public PlanoUsuario buscarPorID(Long id) {
        return planoUsuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Plano de usuário não encontrado com ID: " + id));
    }
}
