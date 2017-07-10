package com.redesprou.redesproulocalizadorfacebook;

/**
 * Created by Fulanuc on 09/07/2017.
 */

public class FacebookPageItem {

    private String nome;
    private String categoria;
    private String identificador;
    private GraphLocation local;
    
retirar a linha do include

retirar a palavra item do nome das variáveis.
Deve ser apenas name, category, id e location.
    public FacebookPageItem(String s, String s1, String s2) {
        nome = s;
        categoria = s1;
        identificador = s2;
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
    
    public String getIdentificador(String identificador) {
        return identificador;
    }
    
    public void setIdentificador() {
        this.identificador = identificador;
    }
    public GraphLocation getLocal() {
        return local;
    }
}
