package com.ITechNovate.asr_backend.service;

import com.ITechNovate.asr_backend.dto.FacturaDTO;
import com.ITechNovate.asr_backend.models_sql.Factura;
import com.ITechNovate.asr_backend.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    // Método para subir una factura desde un archivo XML
    public FacturaDTO saveFacturaFromXml(MultipartFile file) {
        Factura factura = new Factura();
        try {
            // Simulación de procesamiento de XML (debes usar una librería como JAXB)
            factura.setCliente("Cliente del archivo XML");
            factura.setCantidad(BigDecimal.valueOf(100)); // Ejemplo
            factura.setTotal(BigDecimal.valueOf(5000.00));

            // Guardar el archivo XML y persistir los datos
            factura.setArchivoXml(file.getOriginalFilename());
            Factura savedFactura = facturaRepository.save(factura);

            // Convertir entidad Factura a DTO
            return new FacturaDTO(savedFactura.getFolio(), savedFactura.getCliente(), savedFactura.getCantidad(),
                    savedFactura.getTotal(), savedFactura.getArchivoXml());

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar el archivo XML", e);
        }
    }

    // Método para actualizar los datos de una factura
    public FacturaDTO updateFactura(Integer id, FacturaDTO facturaDTO) {
        Factura factura = facturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        factura.setCliente(facturaDTO.getCliente());
        factura.setCantidad(facturaDTO.getCantidad());
        factura.setTotal(facturaDTO.getTotal());

        Factura updatedFactura = facturaRepository.save(factura);
        return new FacturaDTO(updatedFactura.getFolio(), updatedFactura.getCliente(), updatedFactura.getCantidad(),
                updatedFactura.getTotal(), updatedFactura.getArchivoXml());
    }

    // Método para obtener todas las facturas
    public List<FacturaDTO> getAllFacturas() {
        return facturaRepository.findAll().stream()
                .map(factura -> new FacturaDTO(factura.getFolio(), factura.getCliente(), factura.getCantidad(),
                        factura.getTotal(), factura.getArchivoXml()))
                .collect(Collectors.toList());
    }

    // Método para obtener una factura específica por ID
    public FacturaDTO getFacturaById(Integer id) {
        Factura factura = facturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return new FacturaDTO(factura.getFolio(), factura.getCliente(), factura.getCantidad(),
                factura.getTotal(), factura.getArchivoXml());
    }
}