package com.example.viajouapi.services;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.repositorys.AtracaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    // Gerando uma lista de 15 notificações aleatórias
    public List<Atracao> gerarAtracoesAleatorias() {
        List<Atracao> todasAtracoes = buscarAtracoes();
        if (todasAtracoes.isEmpty()) {
            throw new RuntimeException("Nenhuma atração encontrada");
        }

        Random random = new Random();
        List<Atracao> atracoesAleatorias = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Atracao atracaoAleatoria = todasAtracoes.get(random.nextInt(todasAtracoes.size()));
            atracoesAleatorias.add(atracaoAleatoria);
        }
        return atracoesAleatorias;
    }


    // Salvando e atualizando atração
    public Atracao salvarAtracao(Atracao atracao) {
        return atracaoRepository.save(atracao);
    }


}
