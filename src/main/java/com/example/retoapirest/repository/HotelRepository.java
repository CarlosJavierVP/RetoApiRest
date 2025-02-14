package com.example.retoapirest.repository;

import com.example.retoapirest.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, String> {

    public List<Hotel> findAllByCategoria(int estrellas);



}
