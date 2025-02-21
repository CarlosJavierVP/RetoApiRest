package com.example.retoapirest.services;

import com.example.retoapirest.model.Dia;
import com.example.retoapirest.model.TiempoCiudad;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private ObjectMapper objectMapper;

    public JSONObject getTiempoCiudad(String codigoCiudad){
        String url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/"+codigoCiudad+"?api_key="+aemetApiKey;
        String result = "";
        JSONObject outJson = new JSONObject();
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

                // parsear el json a una lista de objetos
                List<TiempoCiudad> aemetDataList = objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, TiempoCiudad.class));

                // filtrar y procesar los datos
                JSONArray diasSalida = new JSONArray();

                JSONObject diaOut = new JSONObject();
                for(TiempoCiudad data: aemetDataList){
                    for(Dia dia : data.getPrediccion().getDia()){
                        diaOut.put("fecha", dia.getFecha());

                        if (dia.getTemperatura() != null){
                            diaOut.put("maximo", data.getPrediccion().getDia().get(0).getTemperatura().getMax());
                            diaOut.put("minimo", data.getPrediccion().getDia().get(0).getTemperatura().getMin());
                        }else {
                            diaOut.put("maximo", "N/A");
                            diaOut.put("minimo", "N/A");
                        }
                    }

                    diasSalida.put(diaOut);
                }

                // añadir los datos filtrados al objeto de salida
                outJson.put("provincia", aemetDataList.get(0).getCiudad());
                outJson.put("dias",diasSalida);

            }else{
                logger.log(Level.SEVERE, "No se ha podido obtener la URL de datos de AEMET");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido obtener la URL de datos de AEMET");
            }
        }catch (RestClientException e){
            logger.log(Level.SEVERE, "Error al obtener los datos de la AEMET", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener los datos meteorológicos de la AEMET");
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return outJson;
    }


}
