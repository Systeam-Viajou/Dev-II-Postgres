package com.example.viajouapi.services;

import com.example.viajouapi.models.PesquisaPerfil;
import com.example.viajouapi.repositorys.PesquisaPerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesquisaPerfilService {
    private final PesquisaPerfilRepository pesquisaPerfilRepository;

    public PesquisaPerfilService(PesquisaPerfilRepository pesquisaPerfilRepository) {
        this.pesquisaPerfilRepository = pesquisaPerfilRepository;
    }

    // Buscar todas as pesquisas
    public List<PesquisaPerfil> buscarPesquisas() {
        return pesquisaPerfilRepository.findAll();
    }

    // Buscar pesquisa por ID
    public PesquisaPerfil buscarPesquisaPorId(Long id) {
        return pesquisaPerfilRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Pesquisa não encontrada"));
    }

    // Buscar pesquisas por UID do usuário
    public PesquisaPerfil buscarPesquisasPorUidUsuario(String uid) {
        return pesquisaPerfilRepository.findByUsuario_Uid(uid).orElseThrow(() ->
                new EntityNotFoundException("Pesquisa perfil não encontrada"));
    }

    // Salvar ou atualizar uma pesquisa
    public PesquisaPerfil salvarPesquisa(PesquisaPerfil pesquisaPerfil) {
        return pesquisaPerfilRepository.save(pesquisaPerfil);
    }
}
