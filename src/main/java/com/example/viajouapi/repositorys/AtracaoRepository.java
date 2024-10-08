package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Atracao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtracaoRepository extends JpaRepository<Atracao, Long> {
    List<Atracao> findByNomeContainsIgnoreCase(String nome);
}
