package com.example.retoapirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TiempoCiudad {
    @Id @JsonProperty("id")
    private String id;
    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("temperatura")
    private Temperatura temperatura;
    @JsonProperty("descripcion")
    private String descripcion;

}
