package com.example.retoapirest.services;

import com.example.retoapirest.model.PuntoInteres;
import com.example.retoapirest.repository.PuntoInteresRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PoiService {
    private final PuntoInteresRepository puntoInteresRepository;


    public PoiService(PuntoInteresRepository puntoInteresRepository) {
        this.puntoInteresRepository = puntoInteresRepository;
    }

    public List<PuntoInteres> obtenerListaPoiLocalizacion(double latitud, double longitud){
        Point ubicacion = new Point(longitud, latitud);
        Distance distMax = new Distance(10, Metrics.KILOMETERS);
        return puntoInteresRepository.findAllByLocalizacion(ubicacion, distMax);
    }

    public PuntoInteres obtenerPoiLocalizacion(double latitud, double longitud){
        Point ubicacion = new Point(longitud, latitud);
        Distance distMax = new Distance(10, Metrics.KILOMETERS);
        return puntoInteresRepository.findByLocalizacion(ubicacion, distMax);
    }
}
