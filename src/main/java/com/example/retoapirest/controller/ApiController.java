package com.example.retoapirest.controller;

import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
