package com.example.viajouapi.services;

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

    public List<Categoria> buscarCategoria(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarCategoriaPorID(Long id){
        return categoriaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Categoria não encontrada"));
    }

    public Categoria salvarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

}
