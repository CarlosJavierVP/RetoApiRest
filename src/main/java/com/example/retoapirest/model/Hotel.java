package com.example.retoapirest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "hoteles")
@Data
public class Hotel {
    @Id
    private String id;
    private String nombre;
    private String direccion;
}
