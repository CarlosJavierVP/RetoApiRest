package com.example.retoapirest.modelo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "hoteles")
@Data
public class Hotel {
    @Id
    private Long _id;
    private String nombre;
    private String direccion;
}
