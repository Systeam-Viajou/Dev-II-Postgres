package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.PesquisaPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesquisaPerfilRepository extends JpaRepository<PesquisaPerfil, Long> {
    // Exemplo de método customizado para buscar pesquisas por usuário
    List<PesquisaPerfil> findByUsuarioUid(String uidUsuario);
}
