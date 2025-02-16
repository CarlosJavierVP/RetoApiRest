package com.example.retoapirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TiempoCiudad {
    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("temperatura")
    private Temperatura temperatura;
    @JsonProperty("descripcion")
    private String descripcion;

}
