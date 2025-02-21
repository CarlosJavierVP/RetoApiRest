package com.example.retoapirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DiaPrediccion {
    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("temperatura")
    private Temperatura temperatura;
}
