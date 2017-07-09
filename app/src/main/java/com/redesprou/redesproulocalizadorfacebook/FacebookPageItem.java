package com.redesprou.redesproulocalizadorfacebook;

/**
 * Created by Fulanuc on 09/07/2017.
 */

public class FacebookPageItem {

    private String nomePagina;
    private String descricaoPagina;

    public FacebookPageItem(String s, String s1) {
        nomePagina = s;
        descricaoPagina = s1;
    }

    public String getNomePagina() {
        return nomePagina;
    }

    public void setNomePagina(String nomePagina) {
        this.nomePagina = nomePagina;
    }

    public String getDescricaoPagina() {
        return descricaoPagina;
    }

    public void setDescricaoPagina(String descricaoPagina) {
        this.descricaoPagina = descricaoPagina;
    }
}
