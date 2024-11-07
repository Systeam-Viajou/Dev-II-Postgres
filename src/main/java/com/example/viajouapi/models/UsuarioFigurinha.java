package com.example.viajouapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_figurinha")
public class UsuarioFigurinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UID_usuario", referencedColumnName = "uid", nullable = false)
    private Usuario idUsuario;

    @Column(name = "ID_figurinha", nullable = false)
    private Long idFigurinha;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdFigurinha() {
        return idFigurinha;
    }

    public void setIdFigurinha(Long idFigurinha) {
        this.idFigurinha = idFigurinha;
    }
}
