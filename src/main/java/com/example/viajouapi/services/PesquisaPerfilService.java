package com.example.viajouapi.services;

import com.example.viajouapi.models.PesquisaPerfil;
import com.example.viajouapi.repositorys.PesquisaPerfilRepository;
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
    public List<PesquisaPerfil> buscarPesquisasPorUidUsuario(String uidUsuario) {
        return pesquisaPerfilRepository.findByUsuarioUid(uidUsuario);
    }

    // Salvar ou atualizar uma pesquisa
    public PesquisaPerfil salvarPesquisa(PesquisaPerfil pesquisaPerfil) {
        return pesquisaPerfilRepository.save(pesquisaPerfil);
    }
}
