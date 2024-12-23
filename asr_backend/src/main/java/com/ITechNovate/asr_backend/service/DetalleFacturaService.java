package com.ITechNovate.asr_backend.service;

import com.ITechNovate.asr_backend.dto.DetalleFacturaDTO;
import com.ITechNovate.asr_backend.models_sql.DetalleFactura;
import com.ITechNovate.asr_backend.models_sql.Factura;
import com.ITechNovate.asr_backend.repository.DetalleFacturaRepository;
import com.ITechNovate.asr_backend.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleFacturaService {
    private final DetalleFacturaRepository detalleFacturaRepository;
    private final FacturaRepository facturaRepository;

    @Autowired
    public DetalleFacturaService(DetalleFacturaRepository detalleFacturaRepository, FacturaRepository facturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
        this.facturaRepository = facturaRepository;
    }

    public List<DetalleFacturaDTO> getAllDetallesFactura() {
        return detalleFacturaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DetalleFacturaDTO> getDetallesByFacturaId(Integer facturaId) {
        return detalleFacturaRepository.findByFactura_Id(facturaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DetalleFacturaDTO getDetalleFacturaById(Integer id) {
        DetalleFactura detalleFactura = detalleFacturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetalleFactura no encontrado con id: " + id));
        return convertToDTO(detalleFactura);
    }

    public DetalleFactura saveDetalleFactura(DetalleFacturaDTO detalleFacturaDTO) {
        DetalleFactura detalleFactura = new DetalleFactura();

        // Buscar la factura por ID
        Factura factura = facturaRepository.findById(detalleFacturaDTO.getFacturaId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + detalleFacturaDTO.getFacturaId()));

        detalleFactura.setFactura(factura); // Asignar la factura
        detalleFactura.setFechaEntrega(detalleFacturaDTO.getFechaEntrega());
        detalleFactura.setFechaVencimiento(detalleFacturaDTO.getFechaVencimiento());
        detalleFactura.setEstatus(DetalleFactura.Estatus.valueOf(detalleFacturaDTO.getEstatus()));
        detalleFactura.setCredito(detalleFacturaDTO.getCredito());
        detalleFactura.setFechaPortal(detalleFacturaDTO.getFechaPortal());
        detalleFactura.setTipoCambio(detalleFacturaDTO.getTipoCambio());

        return detalleFacturaRepository.save(detalleFactura);
    }

    public DetalleFacturaDTO updateDetalleFacturaByFolio(Integer folio, DetalleFacturaDTO detalleFacturaDTO) {
        // Buscar el detalleFactura asociado al folio
        DetalleFactura existing = detalleFacturaRepository.findByFacturaFolio(folio)
                .orElseThrow(() -> new RuntimeException("DetalleFactura no encontrado para el folio: " + folio));

        // Actualizar los campos relevantes
        existing.setFechaEntrega(detalleFacturaDTO.getFechaEntrega());
        existing.setFechaVencimiento(detalleFacturaDTO.getFechaVencimiento());
        existing.setEstatus(DetalleFactura.Estatus.valueOf(detalleFacturaDTO.getEstatus()));
        existing.setCredito(detalleFacturaDTO.getCredito());
        existing.setFechaPortal(detalleFacturaDTO.getFechaPortal());
        existing.setTipoCambio(detalleFacturaDTO.getTipoCambio());

        // Guardar los cambios
        DetalleFactura updated = detalleFacturaRepository.save(existing);

        // Convertir y retornar el DTO actualizado
        return convertToDTO(updated);
    }

    private DetalleFacturaDTO convertToDTO(DetalleFactura detalleFactura) {
        DetalleFacturaDTO dto = new DetalleFacturaDTO();
        dto.setId(detalleFactura.getId());
        dto.setFacturaId(detalleFactura.getFactura().getId()); // Cambio aquí
        dto.setFechaEntrega(detalleFactura.getFechaEntrega());
        dto.setFechaVencimiento(detalleFactura.getFechaVencimiento());
        dto.setEstatus(detalleFactura.getEstatus().name());
        dto.setCredito(detalleFactura.getCredito());
        dto.setFechaPortal(detalleFactura.getFechaPortal());
        dto.setTipoCambio(detalleFactura.getTipoCambio());
        return dto;
    }

    private DetalleFactura convertToEntity(DetalleFacturaDTO dto) {
        DetalleFactura entity = new DetalleFactura();

        // Buscar la factura en el repositorio
        Factura factura = facturaRepository.findById(dto.getFacturaId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + dto.getFacturaId()));

        entity.setFactura(factura); // Asignar la factura
        entity.setFechaEntrega(dto.getFechaEntrega());
        entity.setFechaVencimiento(dto.getFechaVencimiento());
        entity.setEstatus(DetalleFactura.Estatus.valueOf(dto.getEstatus()));
        entity.setCredito(dto.getCredito());
        entity.setFechaPortal(dto.getFechaPortal());
        entity.setTipoCambio(dto.getTipoCambio());

        return entity;
    }
}