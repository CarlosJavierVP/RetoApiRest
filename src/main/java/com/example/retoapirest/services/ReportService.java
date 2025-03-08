package com.example.retoapirest.services;

import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.repository.HotelRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final HotelRepository hotelRepository;

    @Autowired
    public ReportService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public byte[] generarInformeHoteles() {
        try {
            // obtenemos la lista de hoteles
            List<Hotel> listaHoteles = hotelRepository.findAll();

            // formatear la lista de servicios para cada hotel con un mapa - jaspersoft no traga listas
            List<Map<String, Object>> hotelesFormateados = new ArrayList<>();
            // formatear los datos del hotel
            for (Hotel h: listaHoteles){
                Map<String, Object> fila = new HashMap<>();

                StringBuilder sb = new StringBuilder();
                List<String> listaServicios = h.getServicios();
                if (listaServicios != null){
                    for (String s : listaServicios){
                        sb.append(s).append(", ");
                    }
                }else {
                    listaServicios = new ArrayList<>();
                    listaServicios.add("N/A");
                }

                String serviciosCadena = sb.toString();

                fila.put("nombre", h.getNombre());
                fila.put("ciudad", h.getCiudad());
                fila.put("direccion", h.getDireccion());
                fila.put("categoria", h.getCategoria());
                fila.put("servicios", serviciosCadena);
                fila.put("precioNoche", h.getPrecioNoche());
                hotelesFormateados.add(fila);

            }

            // cargamos el archivo .jrxml - ya que jaspersoft no tiene un conector nativo con mongoDB
            InputStream report = getClass().getResourceAsStream("/reports/informeMongoDBservicios.jrxml"); // estoy teniendo en cuenta la lista de servicios

            // compilamos el informe a jasper - en una DB sql lo hacemos nativo con jaspersoft
            JasperReport reportJasper = JasperCompileManager.compileReport(report);

            // A partir de la lista de hoteles generamos una fuente de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(hotelesFormateados); //pasarle la lista formateada

            // generamos el informe
            JasperPrint jp = JasperFillManager.fillReport(reportJasper, null, dataSource);
            // generamos el pdf
            //JasperExportManager.exportReportToPdfFile(jp,"ListadoHoteles.pdf");
            return JasperExportManager.exportReportToPdf(jp);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }



}
