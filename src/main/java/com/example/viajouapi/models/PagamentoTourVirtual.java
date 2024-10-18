package com.example.viajouapi.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagamento_tour_virtual")
public class PagamentoTourVirtual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_usuario", nullable = false)
    private String idUsuario;

    @Column(name = "ID_tourvirtual", nullable = false)
    private int idTourVirtual;

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

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

    public int getIdTourVirtual() {
        return idTourVirtual;
    }

    public void setIdTourVirtual(int idTourVirtual) {
        this.idTourVirtual = idTourVirtual;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
