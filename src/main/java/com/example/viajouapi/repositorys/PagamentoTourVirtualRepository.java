package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.PagamentoTourVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoTourVirtualRepository extends JpaRepository<PagamentoTourVirtual, Long> {
    // Método para buscar pagamentos por ID do usuário
    List<PagamentoTourVirtual> findByIdUsuario(String idUsuario);

    // Método para buscar pagamentos por ID do tour virtual
    List<PagamentoTourVirtual> findByIdTourVirtual(int idTourVirtual);
}
