package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Atracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtracaoRepository extends JpaRepository<Atracao, Long> {
}
