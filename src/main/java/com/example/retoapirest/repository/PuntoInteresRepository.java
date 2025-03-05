package com.example.retoapirest.repository;

import com.example.retoapirest.model.PuntoInteres;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.regex.Pattern;

public interface PuntoInteresRepository extends MongoRepository<PuntoInteres, String> {

    //@Query("{'nombre': {$regex : ?0, $options : 'i'}}")
    //public PuntoInteres findByNombre(Pattern nombre);
    public List<PuntoInteres> findAllByCiudad(String ciudad);
    public List<PuntoInteres> findAllByTipo(String tipo);
    public List<PuntoInteres> findAllByCategorias(String categoria);
    public List<PuntoInteres> findAllByCiudadAndTipo(String ciudad, String tipo);
    public List<PuntoInteres> findAllByCiudadAndCategorias(String ciudad, String categoria);
    @Query("{ 'localizacion': { $nearSphere: { $geometry: { type: 'Point', coordinates: [?0, ?1] }, $maxDistance: ?2 } } }")
    public List<PuntoInteres> findByLocalizacionNear(double longitud, double latitud, double maxDistance);

}
