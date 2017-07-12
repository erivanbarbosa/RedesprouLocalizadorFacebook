package com.redesprou.redesproulocalizadorfacebook.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Erivan on 11/07/2017.
 */

public class PlaceFieldsUtil {



    public static String tratarCategoria( String stringCategoria ){
        String resultado = "";
        String[] strings = stringCategoria.split( "\\}");

        for(int i = 0; i < strings.length; i++  ) {
            String string = strings[i];
            if( i == strings.length - 1 ) {
                resultado += string.substring(string.lastIndexOf(":") + 1);
            }
            else {
                resultado += string.substring(string.lastIndexOf(":") + 1) + ", ";
            }
        }

        resultado = resultado.replace("]", "");

        return resultado;
    }

    public static String tratarCategorias(String categorias) {

        String[] categoriasArray = categorias.split(",");
        ArrayList<String> categoriasLista = new ArrayList<String>();
        String resultado = "";

        for( String categoria : categoriasArray ) {

            if( !categoriasLista.contains(categoria)){
                categoriasLista.add(categoria);
            }
        }

        Iterator<String> iterador = categoriasLista.iterator();

        while( iterador.hasNext() ) {
                     resultado = iterador.next() + ", ";
        }
        return resultado;
    }
}
