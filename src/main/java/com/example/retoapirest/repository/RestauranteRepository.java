package com.example.retoapirest.repository;

import com.example.retoapirest.model.Restaurante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RestauranteRepository extends MongoRepository<Restaurante, String> {

    @Query("{'ciudad': ?0}")
    public List<Restaurante> findAllByCiudad(String ciudad);
}
