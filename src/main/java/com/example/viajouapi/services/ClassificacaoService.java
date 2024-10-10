package com.example.viajouapi.services;

import com.example.viajouapi.models.Classificacao;
import com.example.viajouapi.repositorys.ClassificacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificacaoService {
    private final ClassificacaoRepository classificacaoRpository;

    public ClassificacaoService(ClassificacaoRepository classificacaoRpository){
        this.classificacaoRpository = classificacaoRpository;
    }

    // Buscando todos os eventos
    public List<Classificacao> buscarClassificacao(){
        return classificacaoRpository.findAll();
    }

    // Buscando os eventos pelo id
    public Classificacao buscarClassificacaoPorID(Long id){
        return classificacaoRpository.findById(id).orElseThrow(() ->
                new RuntimeException("Classificação não encontrado"));
    }

    // Salvando e atualizando os eventos
    @Transactional
    public Classificacao salvarClassificacao(Classificacao classificacao){
        return classificacaoRpository.save(classificacao);
    }

}
