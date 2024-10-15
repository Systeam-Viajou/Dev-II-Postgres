package com.example.viajouapi.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pesquisa_perfil")
public class PesquisaPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UID_usuario", referencedColumnName = "uid", nullable = false)
    private Usuario usuario;

    @Column(name = "show", nullable = false)
    private Boolean show = false;

    @Column(name = "festival", nullable = false)
    private Boolean festival = false;

    @Column(name = "exposicao", nullable = false)
    private Boolean exposicao = false;

    @Column(name = "feira", nullable = false)
    private Boolean feira = false;

    @Column(name = "apresentacao", nullable = false)
    private Boolean apresentacao = false;

    @Column(name = "data_resposta", nullable = false, updatable = false)
    private LocalDateTime dataResposta = LocalDateTime.now();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean getFestival() {
        return festival;
    }

    public void setFestival(Boolean festival) {
        this.festival = festival;
    }

    public Boolean getExposicao() {
        return exposicao;
    }

    public void setExposicao(Boolean exposicao) {
        this.exposicao = exposicao;
    }

    public Boolean getFeira() {
        return feira;
    }

    public void setFeira(Boolean feira) {
        this.feira = feira;
    }

    public Boolean getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(Boolean apresentacao) {
        this.apresentacao = apresentacao;
    }

    public LocalDateTime getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDateTime dataResposta) {
        this.dataResposta = dataResposta;
    }
}

