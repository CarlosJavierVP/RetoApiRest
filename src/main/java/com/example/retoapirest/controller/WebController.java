package com.example.retoapirest.controller;


import com.example.retoapirest.repository.EventoRepository;
import com.example.retoapirest.repository.HotelRepository;
import com.example.retoapirest.repository.PuntoInteresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

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

}
