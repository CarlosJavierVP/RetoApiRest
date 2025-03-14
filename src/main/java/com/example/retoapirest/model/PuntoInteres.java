package com.example.retoapirest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "puntoInteres")
@Data
public class PuntoInteres {
    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String direccion;
    private String ciudad;
    private String descripcion;
    private List<String> categorias;
    @GeoSpatialIndexed(name = "localizacion_index")
    private GeoJsonPoint localizacion;


}
