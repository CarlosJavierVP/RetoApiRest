package com.example.retoapirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Temperatura {
    @JsonProperty("maxima")
    private int max;
    @JsonProperty("minima")
    private int min;
}


