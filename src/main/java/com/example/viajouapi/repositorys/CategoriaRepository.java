package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
