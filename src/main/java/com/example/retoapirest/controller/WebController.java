package com.example.retoapirest.controller;


import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.repository.EventoRepository;
import com.example.retoapirest.repository.HotelRepository;
import com.example.retoapirest.repository.PuntoInteresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/web")
public class WebController {

    static final Logger logger = Logger.getLogger(WebController.class.getName());

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    PuntoInteresRepository puntoInteresRepository;

    @GetMapping("/hoteles/")
    public String indexHoteles(Model model){
        var hoteles = hotelRepository.findAll();
        model.addAttribute("titulo", "Listado de Hoteles");
        model.addAttribute("hoteles", hoteles);
        return "indexHoteles";
    }

    @GetMapping("/poi/{id}")
    public String indexPuntoInteres(@PathVariable String id, Model model){
        var poi= puntoInteresRepository.findById(id).get();
        model.addAttribute("titulo", poi.getNombre());
        model.addAttribute("poi", poi);

        return "indexPoi";
    }

    @GetMapping("/hotel/add")
    public String formularioAddHotel(Model model){
        model.addAttribute("hotel", new Object());
        return "formularioHotel";
    }

    @PostMapping("/hotel/add")
    public String addHotel(@ModelAttribute Hotel hotel, Model model) {
        hotelRepository.save(hotel);
        logger.log(Level.INFO, "Hotel añadido: "+hotel);
        model.addAttribute("mensaje", "Hotel añadido correctamente");
        return "formularioHotel";
    }

}
