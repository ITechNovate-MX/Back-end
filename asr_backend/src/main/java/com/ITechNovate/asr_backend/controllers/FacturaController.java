package com.ITechNovate.asr_backend.controllers;

import com.ITechNovate.asr_backend.dto.FacturaDTO;
import com.ITechNovate.asr_backend.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
   private FacturaService facturaService;

    @Operation(summary = "Subir un archivo XML de factura y procesar sus datos",
            description = "Carga un archivo XML de factura, extrae los datos clave y los guarda en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Factura creada con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FacturaDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno al procesar el archivo XML")
    @PostMapping("/upload")
    public ResponseEntity<FacturaDTO> uploadFactura(@RequestParam("file") MultipartFile file) {
        try {
            FacturaDTO facturaDTO = facturaService.saveFacturaFromXml(file);
            return ResponseEntity.ok(facturaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Actualizar los datos de una factura existente",
            description = "Actualiza los datos de una factura usando el ID de la factura y los nuevos valores proporcionados en el cuerpo de la solicitud.")
    @ApiResponse(responseCode = "200", description = "Factura actualizada con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FacturaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> updateFactura(@PathVariable Integer id, @RequestBody FacturaDTO facturaDTO) {
        try {
            FacturaDTO updatedFactura = facturaService.updateFactura(id, facturaDTO);
            return ResponseEntity.ok(updatedFactura);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Obtener todas las facturas",
            description = "Devuelve una lista de todas las facturas almacenadas en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de facturas devuelta con éxito",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FacturaDTO.class))))
    @GetMapping
    public ResponseEntity<List<FacturaDTO>> getAllFacturas() {
        List<FacturaDTO> facturas = facturaService.getAllFacturas();
        return ResponseEntity.ok(facturas);
    }

    @Operation(summary = "Obtener detalles de una factura por su ID",
            description = "Devuelve los detalles completos de una factura específica usando su ID.")
    @ApiResponse(responseCode = "200", description = "Factura encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FacturaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> getFacturaById(@PathVariable Integer id) {
        try {
            FacturaDTO factura = facturaService.getFacturaById(id);
            return ResponseEntity.ok(factura);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}