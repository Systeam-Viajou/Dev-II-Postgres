package com.example.viajouapi.services;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.repositorys.AtracaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtracaoService {
    private final AtracaoRepository atracaoRepository;

    public AtracaoService(AtracaoRepository atracaoRepository) {
        this.atracaoRepository = atracaoRepository;
    }

    // Buscando todas as atrações
    public List<Atracao> buscarAtracoes(){
        return atracaoRepository.findAll();
    }

    // Buscando atração pelo ID
    public Atracao buscarAtracaoPorID(Long id){
        return atracaoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Atração não encontrada"));
    }

    // Salvando e atualizando atração
    public Atracao salvarAtracao(Atracao atracao) {
        return atracaoRepository.save(atracao);
    }


}
