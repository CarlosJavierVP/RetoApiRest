package com.example.retoapirest.controller;

import com.example.retoapirest.model.*;
import com.example.retoapirest.repository.EventoRepository;
import com.example.retoapirest.repository.HotelRepository;
import com.example.retoapirest.repository.PuntoInteresRepository;
import com.example.retoapirest.repository.RestauranteRepository;
import com.example.retoapirest.services.AemetService;
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
    @Autowired
    PuntoInteresRepository puntoInteresRepository;
    @Autowired
    AemetService aemetService;

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

    @GetMapping("/hoteles/precio/{precio}")
    public List<Hotel> findHotelByPrecio(@PathVariable double precio){
        return hotelRepository.findAllByPrecioNoche(precio);
    }

    @PostMapping("/hoteles/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        /*
        ResponseEntity<Hotel> entidad;
        if(hotelRepository.existsById(hotel.getId())){
            entidad = new ResponseEntity<>(HttpStatus.CONFLICT);
        }else {
            hotelRepository.save(hotel);
            entidad = new ResponseEntity<>(HttpStatus.CREATED);
        }

         */
        hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @DeleteMapping("/hoteles/delete/{id}")
    public void deleteHotel(@PathVariable String id){
        hotelRepository.deleteById(id);
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

    @PostMapping("/restaurantes/")
    public ResponseEntity<Restaurante> createRestaurant(@RequestBody Restaurante rest){
        //ResponseEntity<Restaurante> entidad;
        /*
        if (restauranteRepository.existsById(rest.getId())){
            entidad = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            restauranteRepository.save(rest);
            entidad = new ResponseEntity<>(HttpStatus.CREATED);
        }

         */
        restauranteRepository.save(rest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/restaurantes/delete/{id}")
    public void deleteRestaurante(@PathVariable String id){
        restauranteRepository.deleteById(id);
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

    //---------------------------------------------------------------->API PUNTO_DE_INTERES
    @GetMapping("/punto_interes")
    public List<PuntoInteres> allPois(){
        return puntoInteresRepository.findAll();
    }

    @GetMapping("/punto_interes/{id}")
    public ResponseEntity<PuntoInteres> findPoiById(@PathVariable String id){
        ResponseEntity<PuntoInteres> entidad;
        if (puntoInteresRepository.existsById(id)){
            var poi = puntoInteresRepository.findById(id).get();
            entidad = new ResponseEntity<>(poi, HttpStatus.OK);
        }else {
            entidad = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return entidad;
    }

    @GetMapping("/punto_interes/ciudad/{ciudad}")
    public List<PuntoInteres> findPoiByCiudad(@PathVariable String ciudad){
        String ciudadNormalizada = NormalizarCadenas.normalizarMayus(ciudad);
        logger.log(Level.INFO, "Ciudad: "+ciudadNormalizada);
        List<PuntoInteres> pois = puntoInteresRepository.findAllByCiudad(ciudadNormalizada);
        logger.log(Level.INFO, "Nº de puntos de interés en "+ciudadNormalizada+": "+pois.size());
        return pois;
    }

    @GetMapping("/punto_interes/tipo/{tipo}")
    public List<PuntoInteres> findPoiByTipo(@PathVariable String tipo){
        return puntoInteresRepository.findAllByTipo(tipo);
    }

    @GetMapping("/punto_interes/categoria/{categoria}")
    public List<PuntoInteres> findPoiByCategoria(@PathVariable String categoria){
        return puntoInteresRepository.findAllByCategorias(categoria);
    }

    @GetMapping("punto_interes/ciudad/{ciudad}/tipo/{tipo}")
    public List<PuntoInteres> findPoiByCiudadAndTipo(@PathVariable String ciudad, @PathVariable String tipo){
        String ciudadNormalizada = NormalizarCadenas.normalizarMayus(ciudad);

        return puntoInteresRepository.findAllByCiudadAndTipo(ciudadNormalizada,tipo);
    }

    @GetMapping("/punto_interes/ciudad/{ciudad}/categoria/{categoria}")
    public List<PuntoInteres> findPoiByCiudadAndCategoria(@PathVariable String ciudad, @PathVariable String categoria){
        String ciudadNormalizada = NormalizarCadenas.normalizarMayus(ciudad);

        return puntoInteresRepository.findAllByCiudadAndCategorias(ciudadNormalizada, categoria);
    }

    @GetMapping("punto_interes/localizacion") // endpoint ej: http://localhost:8080/api/punto_interes/localizacion?latitud=36.7213&longitud=-4.4155
    public ResponseEntity<List<PuntoInteres>> findPoiByLocalizacion(@RequestParam double latitud, @RequestParam double longitud, @RequestParam(defaultValue = "1000") double maxDist){

        // Obtención de una lista de puntos de interés cercanos a las coordenadas marcadas en el endpoint
        List<PuntoInteres> listaPoi = puntoInteresRepository.findByLocalizacionNear(longitud, latitud, maxDist);
        if (listaPoi.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(listaPoi, HttpStatus.OK);
        }
    }



    //---------------------------------------------------------------->API TIEMPO

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<DiaPrediccion>> getTiempo(@PathVariable String ciudad){
        try{
            List<DiaPrediccion> predicciones = aemetService.getTiempoCiudad(ciudad);
            return ResponseEntity.ok(predicciones);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    /*
    @GetMapping("/ciudad/{ciudad}")
    public String getTiempo (@PathVariable String ciudad){
        return aemetService.getTiempoCiudad(ciudad);
    }

     */

    /*
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<TiempoCiudad> getTiempo (@PathVariable String ciudad){
        TiempoCiudad tc = aemetService.getTiempoCiudad(ciudad);
        return new ResponseEntity<>(tc, HttpStatus.OK);
    }

     */




}
