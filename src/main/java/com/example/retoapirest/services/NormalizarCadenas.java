package com.example.retoapirest.services;

public class NormalizarCadenas {

    public static String normalizarMayus(String cadena){
        // eliminación de espacios y poner a minúsculas
        cadena = cadena.trim().toLowerCase();

        StringBuilder cadenaNormalizada = new StringBuilder();
        boolean mayus = true;

        // convertir la primera letra en mayus
        for(char c : cadena.toCharArray()){
            if(mayus && Character.isLetter(c)){
                cadenaNormalizada.append(Character.toUpperCase(c));
                mayus = false;
            }else {
                cadenaNormalizada.append(c);
            }
        }
        return cadenaNormalizada.toString();
    }

}
