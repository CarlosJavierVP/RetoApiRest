package com.example.retoapirest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gastronomia")
@Data
public class Restaurante {

    @Id
    private String id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String tipoCocina;
}
