package com.ITechNovate.asr_backend.controllers;

import com.ITechNovate.asr_backend.dto.AlertaDTO;
import com.ITechNovate.asr_backend.service.AlertaCreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertaCreditoController {

    @Autowired
    private AlertaCreditoService alertaCreditoService;

    @Operation(summary = "Crear una nueva alerta de crédito",
            description = "Crea una alerta de crédito a partir de los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponse(responseCode = "201", description = "Alerta creada con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlertaDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno al crear la alerta")
    @PostMapping
    public ResponseEntity<AlertaDTO> createAlerta(@RequestBody AlertaDTO alertaDTO) {
        try {
            AlertaDTO createdAlerta = alertaCreditoService.saveAlerta(alertaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAlerta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Actualizar una alerta de crédito existente",
            description = "Actualiza los datos de una alerta existente usando el ID proporcionado.")
    @ApiResponse(responseCode = "200", description = "Alerta actualizada con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlertaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Alerta no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<AlertaDTO> updateAlerta(@PathVariable Integer id, @RequestBody AlertaDTO alertaDTO) {
        try {
            AlertaDTO updatedAlerta = alertaCreditoService.updateAlerta(id, alertaDTO);
            return ResponseEntity.ok(updatedAlerta);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Obtener todas las alertas de crédito",
            description = "Devuelve una lista de todas las alertas de crédito almacenadas en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de alertas devuelta con éxito",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AlertaDTO.class))))
    @GetMapping
    public ResponseEntity<List<AlertaDTO>> getAllAlertas() {
        List<AlertaDTO> alertas = alertaCreditoService.getAllAlertas();
        return ResponseEntity.ok(alertas);
    }

    @Operation(summary = "Obtener los detalles de una alerta específica por ID",
            description = "Devuelve los detalles de una alerta de crédito específica usando su ID.")
    @ApiResponse(responseCode = "200", description = "Alerta encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlertaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Alerta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> getAlertaById(@PathVariable Integer id) {
        try {
            AlertaDTO alerta = alertaCreditoService.getAlertaById(id);
            return ResponseEntity.ok(alerta);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Obtener todas las alertas con correo no enviado",
            description = "Devuelve una lista de alertas cuyo correo aún no ha sido enviado.")
    @ApiResponse(responseCode = "200", description = "Lista de alertas devuelta con éxito",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AlertaDTO.class))))
    @GetMapping("/no-enviadas")
    public ResponseEntity<List<AlertaDTO>> getAlertasNoEnviadas() {
        List<AlertaDTO> alertasNoEnviadas = alertaCreditoService.getAlertasNoEnviadas();
        return ResponseEntity.ok(alertasNoEnviadas);
    }

    @Operation(summary = "Marcar una alerta como enviada",
            description = "Actualiza el estado de una alerta a 'enviada' usando su ID.")
    @ApiResponse(responseCode = "200", description = "Alerta marcada como enviada con éxito")
    @ApiResponse(responseCode = "404", description = "Alerta no encontrada")
    @PutMapping("/{id}/marcar-enviada")
    public ResponseEntity<Void> marcarAlertaComoEnviada(@PathVariable Integer id) {
        try {
            alertaCreditoService.marcarCorreoEnviado(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}