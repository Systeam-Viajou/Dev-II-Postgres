package com.example.viajouapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "plano_usuario")
public class PlanoUsuario {

    @Id
    @Column(name = "ID_plano")
    private Long planoId;

    @Id
    @Column(name = "ID_usuario")
    private String usuarioId;

    @NotNull(message = "Data de pagamento é obrigatória")
    @Column(name = "data_pagamento")
    private ZonedDateTime dataPagamento;

    @NotNull(message = "Data de término é obrigatória")
    @Column(name = "data_termino")
    private ZonedDateTime dataTermino;

    // Getters and Setters
    public Long getPlanoId() {
        return planoId;
    }

    public void setPlanoId(Long planoId) {
        this.planoId = planoId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ZonedDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(ZonedDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public ZonedDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(ZonedDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }
}
