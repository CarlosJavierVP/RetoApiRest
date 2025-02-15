package com.example.retoapirest.controller;

import com.example.retoapirest.model.Evento;
import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.model.Restaurante;
import com.example.retoapirest.repository.EventoRepository;
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
    @Autowired
    EventoRepository eventoRepository;

    //---------------------------------------------------------------->API HOTELES
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

    //---------------------------------------------------------------->API RESTAURANTES
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

    //---------------------------------------------------------------->API EVENTOS
    @GetMapping("/eventos")
    public List<Evento> allEvent(){
        return eventoRepository.findAll();
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<Evento> findEventById(@PathVariable String id){
        ResponseEntity<Evento> entidad;
        if(eventoRepository.existsById(id)){
            var evento = eventoRepository.findById(id).get();
            entidad = new ResponseEntity<>(evento, HttpStatus.OK);
        }else {
            entidad = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return entidad;
    }

    @GetMapping("/eventos/tipo/{tipo}")
    public List<Evento> findEventByTipo(@PathVariable String tipo){
        logger.log(Level.INFO, "Tipo de evento: "+tipo);
        List<Evento> eventos = eventoRepository.findAllByTipo(tipo);
        logger.log(Level.INFO, "Nº de eventos de tipo "+tipo+": "+eventos.size());
        return eventos;
    }

    @GetMapping("/eventos/ciudad/{ciudad}")
    public List<Evento> findEventByCiudad(@PathVariable String ciudad){
        logger.log(Level.INFO, "Ciudad: "+ciudad);
        String ciudadNormalizada = NormalizarCadenas.normalizarMayus(ciudad);
        List<Evento> eventos = eventoRepository.findAllByCiudad(ciudadNormalizada);
        logger.log(Level.INFO, "Eventos encontrados: "+eventos.size());
        return eventos;
    }

    @GetMapping("/eventos/ciudad/{ciudad}/tipo/{tipo}")
    public List<Evento> findEventByTipoAndCiudad(@PathVariable String ciudad, @PathVariable String tipo){
        String ciudadNormalizada = NormalizarCadenas.normalizarMayus(ciudad);
        logger.log(Level.INFO, "Ciudad: "+ciudadNormalizada);
        List<Evento> eventos = eventoRepository.findAllByCiudadAndTipo(ciudadNormalizada, tipo);
        logger.log(Level.INFO, "Nº de eventos de tipo "+tipo+": "+eventos.size());
        return eventos;
    }

}
