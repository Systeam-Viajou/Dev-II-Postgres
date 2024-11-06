package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.Classificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassificacaoRepository extends JpaRepository<Classificacao, Long> {

    Optional<Classificacao> findByUsuario_Uid(String uid);
    Optional<List<Classificacao>> findByAtracao_Id(Long id);

    @Procedure(name = "avaliar_atracao")
    void avaliarAtracao(
            @Param("v_nota") Float valorNota,
            @Param("v_uid_usuario") String v_uid_usuario,
            @Param("v_id_atracao") Integer v_id_atracao
    );
}
