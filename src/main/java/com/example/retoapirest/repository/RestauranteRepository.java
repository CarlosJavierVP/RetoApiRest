package com.example.retoapirest.repository;

import com.example.retoapirest.model.Restaurante;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestauranteRepository extends MongoRepository<Restaurante, String> {

    public List<Restaurante> findAllByCiudad(String ciudad);
}
