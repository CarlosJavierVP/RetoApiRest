package com.example.retoapirest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DiaPrediccion {
    @JsonProperty("dia")
    private List<Dia> dia;



}
