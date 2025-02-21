package com.example.retoapirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Dia {
    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("Temperatura")
    private Temperatura temperatura;
}
