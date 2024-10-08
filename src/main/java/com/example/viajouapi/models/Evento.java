package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicio", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Timestamp dataInicio;

    @Column(name = "data_termino", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Timestamp dataTermino;

    @DecimalMin(value = "0.00", message = "O preço por pessoa deve ser no mínimo 0.00.")
    @DecimalMax(value = "99999999.99", message = "O preço por pessoa deve ser no máximo 99999999.99.")
    @Column(name = "preco_pessoa", precision = 10, scale = 2)
    private BigDecimal precoPessoa;

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

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Timestamp getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Timestamp dataTermino) {
        this.dataTermino = dataTermino;
    }

    public BigDecimal getPrecoPessoa() {
        return precoPessoa;
    }

    public void setPrecoPessoa(BigDecimal precoPessoa) {
        this.precoPessoa = precoPessoa;
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

