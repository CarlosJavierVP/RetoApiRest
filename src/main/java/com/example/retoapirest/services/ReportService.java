package com.example.retoapirest.services;

import com.example.retoapirest.model.Hotel;
import com.example.retoapirest.repository.HotelRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.List;

@Service
public class ReportService {

    private final HotelRepository hotelRepository;

    @Autowired
    public ReportService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void generarInformeHoteles() {
        try {
            // obtenemos la lista de hoteles
            List<Hotel> listaHoteles = hotelRepository.findAll();

            // cargamos el archivo .jrxml - ya que jaspersoft no tiene un conector nativo con mongoDB
            InputStream report = getClass().getResourceAsStream("/reports/informeMongoDB.jrxml");

            // compilamos el informe a jasper - en una DB sql lo hacemos nativo con jaspersoft
            JasperReport reportJasper = JasperCompileManager.compileReport(report);

            // A partir de la lista de hoteles generamos una fuente de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaHoteles);

            // generamos el informe
            JasperPrint jp = JasperFillManager.fillReport(reportJasper, null, dataSource);
            // generamos el pdf
            JasperExportManager.exportReportToPdfFile("ListadoHoteles.pdf");

        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }



}
