package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNomeContainsIgnoreCase(String nome);
}
