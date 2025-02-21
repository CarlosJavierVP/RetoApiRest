package com.example.retoapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class TiempoCiudad {
    @JsonProperty("provincia")
    private String ciudad;
    @JsonProperty("elaborado")
    private String fecha;
    @JsonProperty("temperatura")
    private Temperatura temperatura;

}
