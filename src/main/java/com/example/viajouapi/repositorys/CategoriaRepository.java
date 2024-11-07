package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Atracao;
import com.example.viajouapi.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<List<Categoria>> findByNomeContainsIgnoreCase(String nome);
}
