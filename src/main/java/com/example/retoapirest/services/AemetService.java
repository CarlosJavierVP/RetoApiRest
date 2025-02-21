package com.example.retoapirest.services;

import com.example.retoapirest.model.DiaPrediccion;
import com.example.retoapirest.model.TiempoCiudad;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AemetService {

    static final Logger logger = Logger.getLogger(AemetService.class.getName());

    @Value("${aemet.api.key}")
    private String aemetApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<DiaPrediccion> getTiempoCiudad(String codigoCiudad){
        String url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/"+codigoCiudad+"?api_key="+aemetApiKey;
        String result = "";
        List<DiaPrediccion> predicciones = new ArrayList<>();
        // AEMET devuelve un json con url temporal y hace una segunda petición a dicha url para obtener los datos reales
        try{
            // primera peticion GET
            logger.log(Level.INFO, " URL api: "+url);
            ResponseEntity<Map> response = restTemplate.getForEntity(url,Map.class);
            logger.log(Level.INFO, "Resultado de la api: "+response.getBody());

            // AEMET: la clave es "datos"
            if (response.getBody() != null && response.getBody().containsKey("datos")){
                // URL temporal
                String urlDatos = (String) response.getBody().get("datos");
                logger.log(Level.INFO, "URL temporal: "+urlDatos);

                // segunda petición GET
                result = restTemplate.getForObject(urlDatos, String.class);

                // parsear el json a la
                ObjectMapper objMap = new ObjectMapper();
                JsonNode rootNodo = objMap.readTree(result);
                JsonNode prediccionNodo = rootNodo.path("prediccion");

                if(prediccionNodo.isArray()){
                    for(JsonNode diaNodo : prediccionNodo){
                        DiaPrediccion dia = objMap.treeToValue(diaNodo, DiaPrediccion.class);
                        predicciones.add(dia);
                    }
                }else{
                    logger.log(Level.SEVERE, "No se ha podido obtener la URL de datos de AEMET");
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido obtener la URL de datos de AEMET");
                }

            }else{
                logger.log(Level.SEVERE, "No se ha podido obtener la URL de datos de AEMET");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido obtener la URL de datos de AEMET");
            }
        }catch (RestClientException e){
            logger.log(Level.SEVERE, "Error al obtener los datos de la AEMET", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener los datos meteorológicos de la AEMET");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return predicciones;
    }


}
