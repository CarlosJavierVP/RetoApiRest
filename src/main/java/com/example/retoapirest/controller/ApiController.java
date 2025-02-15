package com.example.retoapirest.controller;

import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.model.Restaurante;
import com.example.retoapirest.repository.HotelRepository;
import com.example.retoapirest.repository.RestauranteRepository;
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
    @Autowired
    RestauranteRepository restauranteRepository;


    @GetMapping("/hoteles")
    public List<Hotel> allHotel(){
        return hotelRepository.findAll();
    }

    @GetMapping("/hoteles/{id}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable String id){
        ResponseEntity<Hotel> entidad;
        if (hotelRepository.existsById(id)) {
            var hotel = hotelRepository.findById(id).get();
            entidad = new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
        }else {
            entidad = new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
        }
        return entidad;
    }

    @GetMapping("/hoteles/categoria/{categoria}")
    public List<Hotel> findHotelByEstrellas(@PathVariable int categoria){
        return hotelRepository.findAllByCategoria(categoria);
    }

    @GetMapping("/restaurantes")
    public List<Restaurante> allRestaurant(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/restaurantes/{id}")
    public ResponseEntity<Restaurante> findRestaurantById(@PathVariable String id){
        ResponseEntity<Restaurante> entidad;
        if(restauranteRepository.existsById(id)){
            var restaurant = restauranteRepository.findById(id).get();
            entidad = new ResponseEntity<>(restaurant, HttpStatus.OK);
        }else {
            entidad = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return entidad;
    }

    public List<Restaurante> findByCiudad(@PathVariable String ciudad){



        return restauranteRepository.findAllByCiudad(ciudad);
    }


}
