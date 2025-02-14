package com.example.retoapirest.controller;

import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/")
    public List<Hotel> all(){
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable String id){
        ResponseEntity<Hotel> entidad;
        if (hotelRepository.existsById(id)) {
            var hotel = hotelRepository.findById(id).get();
            entidad = new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
        }else {
            entidad = new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
        }
        return entidad;
    }

    @GetMapping("/categoria/{categoria}")
    public List<Hotel> findByEstrellas(@PathVariable int categoria){
        return hotelRepository.findAllByCategoria(categoria);
    }

}
