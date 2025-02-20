package com.example.retoapirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AemetService {
/*
    static final Logger logger = Logger.getLogger(AemetService.class.getName());

    @Value("${aemet.api.key}")
    private String aemetApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public String getTiempoCiudad(String codigoCiudad){
        String url = "https://opendata.aemet.es/opendata/api/preccion/especifica/municipio/diaria/"+codigoCiudad+"?api_key="+aemetApiKey;
        String result = "";
        try{
            // peticion GET
            logger.log(Level.INFO, " URL api: "+url);
            result = restTemplate.getForObject(url, String.class);
            logger.log(Level.INFO, "Resultado de la api: "+result);

        }catch (RestClientException e){
            logger.log(Level.SEVERE, "Error al obtener los datos de la AEMET", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener los datos meteorológicos de la AEMET");
        }
        return result;
    }

 */
}
