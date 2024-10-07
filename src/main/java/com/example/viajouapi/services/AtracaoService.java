package com.example.viajouapi.services;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.repositorys.AtracaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtracaoService {
    private final AtracaoRepository atracaoRepository;

    public AtracaoService(AtracaoRepository atracaoRepository) {
        this.atracaoRepository = atracaoRepository;
    }

    public List<Atracao> buscarAtracoes(){
        return atracaoRepository.findAll();
    }

    public Atracao buscarAtracaoPorID(Long id){
        return atracaoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Atração não encontrada"));
    }

    public Atracao salvarAtracao(Atracao atracao) {
        return atracaoRepository.save(atracao);
    }
}
