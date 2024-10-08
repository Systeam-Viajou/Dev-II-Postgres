package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

@Entity
@Table(name = "classificacao")
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "1.0", message = "A nota mínima é 1.0.")
    @DecimalMax(value = "5.0", message = "A nota máxima é 5.0.")
    @Column(name = "nota", nullable = false)
    private Float nota;

    @NotNull(message = "O ID do usuário é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "ID_usuario", referencedColumnName = "id", nullable = false)
    private Usuario uidUsuario;

    @NotNull(message = "A atração é obrigatória.")
    @ManyToOne
    @JoinColumn(name = "ID_atracao", referencedColumnName = "id", nullable = false)
    private Atracao atracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public Usuario getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(Usuario uidUsuario) {
        this.uidUsuario = uidUsuario;
    }

    public Atracao getAtracao() {
        return atracao;
    }

    public void setAtracao(Atracao atracao) {
        this.atracao = atracao;
    }
}
