package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "evento")
public class    Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicio")
    private ZonedDateTime dataInicio;

    @Column(name = "data_termino")
    private ZonedDateTime dataTermino;

    @DecimalMin(value = "0.00", message = "O preço por pessoa deve ser no mínimo 0.00.")
    @DecimalMax(value = "99999999.99", message = "O preço por pessoa deve ser no máximo 99999999.99.")
    @Column(name = "preco_pessoa", precision = 10, scale = 2)
    private BigDecimal precoPessoa;

    @NotNull(message = "A atração é obrigatória.")
    @ManyToOne
    @JoinColumn(name = "ID_atracao", referencedColumnName = "id", nullable = false)
    private Atracao atracao;

    @Column(name = "data_desativacao")
    private ZonedDateTime dataDesativacao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(ZonedDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public ZonedDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(ZonedDateTime dataTermino) {
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

    public ZonedDateTime getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(ZonedDateTime dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }
}
