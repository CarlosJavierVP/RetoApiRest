package com.example.retoapirest.repository;

import com.example.retoapirest.model.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventoRepository extends MongoRepository<Evento, String> {

    public List<Evento> findAllByTipo(String tipo);
    public List<Evento> findAllByCiudad(String ciudad);
    public List<Evento> findAllByCiudadAndTipo(String ciudad, String tipo);
}
