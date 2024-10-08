package com.example.viajouapi.services;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import com.example.viajouapi.models.Usuario;
import com.example.viajouapi.repositorys.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    private CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    // Buscando todas as categorias
    public List<Categoria> buscarCategoria(){
        return categoriaRepository.findAll();
    }

    // Buscando pelo nome, ele pode estar em qualquer lugar da linha
    public List<Categoria> buscarCategoriaPorNome(String nome){
        return categoriaRepository.findByNomeContainsIgnoreCase(nome);
    }

    // Buscando categoria pelo id
    public Categoria buscarCategoriaPorID(Long id){
        return categoriaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Categoria não encontrada"));
    }

    // Salvando e atualizando categoria
    public Categoria salvarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

}
