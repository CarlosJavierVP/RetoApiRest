package com.example.retoapirest.services;

public class NormalizarCiudad {

    public static String normalizarCiudades(String ciudad){
        // eliminación de espacios y poner a minúsculas
        ciudad = ciudad.trim().toLowerCase();

        StringBuilder ciudadNormalizada = new StringBuilder();
        boolean mayus = true;

        // convertir la primera letra en mayus
        for(char c : ciudad.toCharArray()){
            if(mayus && Character.isLetter(c)){
                ciudadNormalizada.append(Character.toUpperCase(c));
                mayus = false;
            }else {
                ciudadNormalizada.append(c);
            }
        }
        return ciudadNormalizada.toString();
    }

}
