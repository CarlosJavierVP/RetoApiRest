package com.example.retoapirest.repository;

import com.example.retoapirest.model.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventoRepository extends MongoRepository<Evento, String> {

}
