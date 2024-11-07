package com.example.viajouapi.services;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.repositorys.AtracaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        // Busca todas as atrações
        List<Atracao> todasAtracoes = buscarAtracoes();

        // Filtra apenas as atrações com tipo não nulo e com nome "evento", "tour-virtual" ou "ponto-turistico"
        List<Atracao> atracoesFiltradas = todasAtracoes.stream()
                .filter(atracao -> atracao.getTipo() != null &&
                        ("evento".equalsIgnoreCase(atracao.getTipo().getNome())
                                || "tour-virtual".equalsIgnoreCase(atracao.getTipo().getNome())
                                || "ponto-turistico".equalsIgnoreCase(atracao.getTipo().getNome())))
                .collect(Collectors.toList());

        // Verifica se há atrações suficientes
        if (atracoesFiltradas.size() < 15) {
            throw new RuntimeException("Atrações insuficientes para seleção aleatória.");
        }

        // Seleciona 15 atrações aleatórias
        Random random = new Random();
        List<Atracao> atracoesAleatorias = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Atracao atracaoAleatoria = atracoesFiltradas.get(random.nextInt(atracoesFiltradas.size()));
            atracoesAleatorias.add(atracaoAleatoria);
        }

        return atracoesAleatorias;
    }



    // Salvando e atualizando atração
    public Atracao salvarAtracao(Atracao atracao) {
        return atracaoRepository.save(atracao);
    }


}
