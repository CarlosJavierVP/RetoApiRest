package com.example.retoapirest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "eventos")
@Data
public class Evento {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String ciudad;
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precio;
}
