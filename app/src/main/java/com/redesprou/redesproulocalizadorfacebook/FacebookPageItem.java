package com.redesprou.redesproulocalizadorfacebook;

/**
 * Created by Fulanuc on 09/07/2017.
 */

public class FacebookPageItem {

    private String nome;
    private String categoria;
    private String id;
    private GraphObject local;
    
    
    
    public FacebookPageItem(String infoNome, String infoCategoria, String infoId) {
        nome = infoNome;
        categoria = infoCategoria;
        id = infoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCategoria(String categoria) {
        return categoria;
    }
    
    public void setCategoria() {
        this.categoria = categoria;
    }
    
    public String getId(String id) {
        return id;
    }
    
    public void setId() {
        this.id = id;
    }
    public GraphObject getLocal() {
        return local;
    }
}
