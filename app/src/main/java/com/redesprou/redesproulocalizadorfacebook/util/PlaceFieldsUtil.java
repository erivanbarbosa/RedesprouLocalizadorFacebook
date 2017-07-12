package com.redesprou.redesproulocalizadorfacebook.util;

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




}
