package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
    Optional<List<Tipo>> findByNomeContainsIgnoreCase(String nome);
}
