package com.example.retoapirest.controller;


import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.repository.HotelRepository;
import com.example.retoapirest.repository.PuntoInteresRepository;
import com.example.retoapirest.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase WebController
 * Pasa los datos de la BD a una plantilla html
 */
@Controller
@RequestMapping("/web")
public class WebController {
    /** Atributo statico para las trazas logger */
    static final Logger logger = Logger.getLogger(WebController.class.getName());

    private final ReportService reportService;

    /** Inyeccion repository de hotel */
    @Autowired
    HotelRepository hotelRepository;
    /** Inyeccion repository de poi */
    @Autowired
    PuntoInteresRepository puntoInteresRepository;

    public WebController(ReportService reportService) {
        this.reportService = reportService;
    }


    /**
     * Metodo indexHoteles que devuelve en una plantilla html un listado de hoteles
     * @param model model
     * @return plantilla html tabla de todos los hoteles
     */
    @GetMapping("/hoteles/")
    public String indexHoteles(Model model){
        var hoteles = hotelRepository.findAll();
        model.addAttribute("titulo", "Listado de Hoteles");
        model.addAttribute("hoteles", hoteles);
        return "indexHoteles";
    }

    /**
     * Metodo indexPuntoInteres que devuelve una plantilla html de un punto de interés turístico
     * @param id id del poi
     * @param model model
     * @return plantilla html tarjeta con los datos de un poi por su id
     */
    @GetMapping("/poi/{id}")
    public String indexPuntoInteres(@PathVariable String id, Model model){
        var poi= puntoInteresRepository.findById(id).get();
        model.addAttribute("titulo", poi.getNombre());
        model.addAttribute("poi", poi);

        return "indexPoi";
    }

    /**
     * Metodo formularioAddHotel la vista del formulario para añadir un hotel a la DB
     * @param model model
     * @return formulario html
     */
    @GetMapping("/hotel/add")
    public String formularioAddHotel(Model model){
        model.addAttribute("hotel", new Hotel());
        return "formularioHotel";
    }

    /**
     * Metodo addHotel que recoge el objeto Hotel del formulario y lo almacena en la BD
     * @param hotel hotel
     * @param model model
     * @return regresa al html del formulario con un mensaje de que el hotel se ha añadido correctamente.
     */

    @PostMapping("/hotel/add")
    public String addHotel(@ModelAttribute Hotel hotel, Model model) {
        hotelRepository.save(hotel);
        logger.log(Level.INFO, "Hotel añadido: "+hotel.getNombre());
        model.addAttribute("mensaje", "Hotel añadido correctamente");
        return "formularioHotel";
    }


    /*
    @ResponseBody
    @RequestMapping(value = "/hotel/add", method = RequestMethod.POST)
    public Hotel guardarHotel(@ModelAttribute Hotel hotel, Model model){
        hotelRepository.save(hotel);
        logger.log(Level.INFO, "Hotel añadido: "+hotel.getNombre());
        model.addAttribute("mensaje", "Hotel añadido correctamente");
        return hotel;
    }
     */

    @GetMapping("/hoteles/descargar")
    public void descargarInforme() {
        reportService.generarInformeHoteles();
    }




}
