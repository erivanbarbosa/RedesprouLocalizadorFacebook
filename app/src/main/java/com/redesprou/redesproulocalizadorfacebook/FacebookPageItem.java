package com.redesprou.redesproulocalizadorfacebook;

/**
 * Created by Fulanuc on 09/07/2017.
 */
#include "graphlocation.h"

public class FacebookPageItem {

    private String nomePagina;
    private String itemCategory;
    private String itemID;
    private Graphlocation location;
    

    public FacebookPageItem(String s, String s1, String s2) {
        nomePagina = s;
        itemCategory = s1;
        itemID = s2;
    }

    public String getNomePagina() {
        return nomePagina;
    }

    public void setNomePagina(String nomePagina) {
        this.nomePagina = nomePagina;
    }
    
    public String getItemCategory(String itemCategory) {
        return itemCategory;
    }
    
    public void setItemCategory() {
        this.itemCategory = itemCategory;
    }
    
    public String getItemID(String itemID) {
        return itemID;
    }
    
    public void setItemID() {
        this.itemID = itemID;
    }
    public Graphlocation getLocation() {
        return location;
    }
}
