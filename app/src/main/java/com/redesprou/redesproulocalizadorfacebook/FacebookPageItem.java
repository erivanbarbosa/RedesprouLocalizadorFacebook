package com.redesprou.redesproulocalizadorfacebook;

/**
 * Created by Fulanuc on 09/07/2017.
 */

public class FacebookPageItem {

    private String nome;
    private String id;
    private String telefone;
    private String local;
    private String categoria;
    private String descricao;
    private String capa;
    private boolean verificada;
    private String engajamento;
    private Double media;

    public FacebookPageItem(String nome, String id, String telefone, String local, String categoria,
                            String descricao, String capa, boolean verificada, String engajamento, Double media) {
        this.nome = nome;
        this.id = id;
        this.telefone = telefone;
        this.local = local;
        this.categoria = categoria;
        this.descricao = descricao;
        this.capa = capa;
        this.verificada = verificada;
        this.engajamento = engajamento;
        this.media = media;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public boolean isVerificada() {
        return verificada;
    }

    public void setVerificada(boolean verificada) {
        this.verificada = verificada;
    }

    public String getEngajamento() {
        return engajamento;
    }

    public void setEngajamento(String engajamento) {
        this.engajamento = engajamento;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }
}
