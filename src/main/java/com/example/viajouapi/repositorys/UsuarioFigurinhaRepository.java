package com.example.viajouapi.repositorys;

import com.example.viajouapi.models.UsuarioFigurinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioFigurinhaRepository extends JpaRepository<UsuarioFigurinha, Long> {
    // Método para buscar figurinha pelo ID do usuário
    Optional<List<UsuarioFigurinha>> findByIdUsuario_Uid(String uid);
}
