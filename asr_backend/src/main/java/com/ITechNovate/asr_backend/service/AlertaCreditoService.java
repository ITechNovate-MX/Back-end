package com.ITechNovate.asr_backend.service;

import com.ITechNovate.asr_backend.dto.AlertaDTO;
import com.ITechNovate.asr_backend.models_sql.AlertaCredito;
import com.ITechNovate.asr_backend.repository.AlertaCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaCreditoService {

    @Autowired
    private AlertaCreditoRepository alertaCreditoRepository;

    // Metodo para crear una alerta de crédito
    public AlertaDTO saveAlerta(AlertaDTO alertaDTO) {
        AlertaCredito alerta = new AlertaCredito();
        alerta.setFacturaId(alertaDTO.getFacturaId());
        alerta.setFechaAlerta(alertaDTO.getFechaAlerta());
        alerta.setUsuarioId(alertaDTO.getUsuarioId());
        alerta.setCorreoEnviado(alertaDTO.getCorreoEnviado());

        AlertaCredito savedAlerta = alertaCreditoRepository.save(alerta);
        return convertToDTO(savedAlerta);
    }

    // Método para actualizar una alerta de crédito existente
    public AlertaDTO updateAlerta(Integer id, AlertaDTO alertaDTO) {
        AlertaCredito alerta = alertaCreditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));

        alerta.setFechaAlerta(alertaDTO.getFechaAlerta());
        alerta.setCorreoEnviado(alertaDTO.getCorreoEnviado());

        AlertaCredito updatedAlerta = alertaCreditoRepository.save(alerta);
        return convertToDTO(updatedAlerta);
    }

    // Método para obtener todas las alertas de crédito
    public List<AlertaDTO> getAllAlertas() {
        return alertaCreditoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener una alerta específica por ID
    public AlertaDTO getAlertaById(Integer id) {
        AlertaCredito alerta = alertaCreditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
        return convertToDTO(alerta);
    }

    // Método para obtener todas las alertas con correo no enviado
    public List<AlertaDTO> getAlertasNoEnviadas() {
        return alertaCreditoRepository.findByCorreoEnviadoFalse().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para marcar una alerta como enviada
    public void marcarCorreoEnviado(Integer id) {
        AlertaCredito alerta = alertaCreditoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
        alerta.setCorreoEnviado(true);
        alertaCreditoRepository.save(alerta);
    }

    // Conversión de entidad AlertaCredito a DTO
    private AlertaDTO convertToDTO(AlertaCredito alerta) {
        return new AlertaDTO(
                alerta.getFacturaId(),
                alerta.getFechaAlerta(),
                alerta.getUsuarioId(),
                alerta.getCorreoEnviado()
        );
    }
}
