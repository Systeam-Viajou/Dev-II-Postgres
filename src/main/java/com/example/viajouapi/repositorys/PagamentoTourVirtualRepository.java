package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.PagamentoTourVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoTourVirtualRepository extends JpaRepository<PagamentoTourVirtual, Long> {
}
