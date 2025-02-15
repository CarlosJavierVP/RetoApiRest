package com.example.retoapirest.controller;

import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.model.Restaurante;
import com.example.retoapirest.repository.HotelRepository;
import com.example.retoapirest.repository.RestauranteRepository;
import com.example.retoapirest.services.NormalizarCadenas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ApiController {

    static final Logger logger = Logger.getLogger(ApiController.class.getName());

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

    @GetMapping("/restaurantes/ciudad/{ciudad}")
    public List<Restaurante> findByCiudad(@PathVariable String ciudad){
        logger.log(Level.INFO, "Ciudad: "+ciudad);
        String ciudadNormalizada = NormalizarCadenas.normalizarMayus(ciudad);
        List<Restaurante> restaurantes = restauranteRepository.findAllByCiudad(ciudadNormalizada);
        logger.log(Level.INFO, "Restaurantes encontrados: "+restaurantes.size());
        return restaurantes;
    }

    @GetMapping("/restaurantes/cocina/{cocina}")
    public List<Restaurante> findByCocina(@PathVariable String cocina){
        logger.log(Level.INFO,"Cocina: "+cocina);
        String cocinaNormalizada = NormalizarCadenas.normalizarMayus(cocina);
        List<Restaurante> restaurantes = restauranteRepository.findAllByTipoCocina(cocinaNormalizada);
        logger.log(Level.INFO, "Nº de restaurantes con tipo de cocina "+cocinaNormalizada+": "+restaurantes.size());
        return restaurantes;
    }


}
