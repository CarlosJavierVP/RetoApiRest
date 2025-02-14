package com.example.retoapirest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "museos")
@Data
public class Museo {
    @Id
    private String id;
    private String nombre;
    private String direccion;
    private String tematico;
}
