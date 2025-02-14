package com.example.retoapirest.repository;

import com.example.retoapirest.model.Museo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuseoRepository extends MongoRepository<Museo, String> {
}
