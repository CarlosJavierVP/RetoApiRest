package com.example.retoapirest.repository;

import com.example.retoapirest.model.PuntoInteres;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PuntoInteresRepository extends MongoRepository<PuntoInteres, String> {

    public List<PuntoInteres> findAllByCiudad(String ciudad);
    public List<PuntoInteres> findAllByTipo(String tipo);
    public List<PuntoInteres> findAllByCategorias(String categoria);
    public List<PuntoInteres> findAllByCiudadAndTipo(String ciudad, String tipo);
    public List<PuntoInteres> findAllByCiudadAndCategorias(String ciudad, String categoria);
}
