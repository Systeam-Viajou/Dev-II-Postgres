package com.example.viajouapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_figurinha")
public class UsuarioFigurinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_usuario", nullable = false)
    private String idUsuario;

    @Column(name = "ID_figurinha", nullable = false)
    private int idFigurinha;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdFigurinha() {
        return idFigurinha;
    }

    public void setIdFigurinha(int idFigurinha) {
        this.idFigurinha = idFigurinha;
    }
}
