package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "ponto_turistico")
public class PontoTuristico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A atração é obrigatória.")
    @ManyToOne
    @JoinColumn(name = "ID_atracao", referencedColumnName = "id", nullable = false)
    private Atracao atracao;

    @Column(name = "data_desativacao")
    private LocalDateTime dataDesativacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Atracao getAtracao() {
        return atracao;
    }

    public void setAtracao(Atracao atracao) {
        this.atracao = atracao;
    }

    public LocalDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(LocalDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
}

