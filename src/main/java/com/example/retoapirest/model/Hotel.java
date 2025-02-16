package com.example.retoapirest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "hoteles")
@Data
public class Hotel {
    @Id
    private String id;
    private String nombre;
    private String ciudad;
    private String direccion;
    private int categoria;
    private List<String> servicios;
    private double precioNoche;
}
