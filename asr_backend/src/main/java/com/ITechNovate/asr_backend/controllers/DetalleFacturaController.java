package com.ITechNovate.asr_backend.controllers;

import com.ITechNovate.asr_backend.dto.DetalleFacturaDTO;
import com.ITechNovate.asr_backend.models_sql.DetalleFactura;
import com.ITechNovate.asr_backend.service.DetalleFacturaService;
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

import java.util.List;

@RestController
@RequestMapping("/detallefactura")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Operation(summary = "Obtener todos los Detalles de Factura",
            description = "Devuelve una lista de todos los Detalles de Factura almacenados en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de Detalles de Factura devuelta con éxito",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DetalleFacturaDTO.class))))
    @GetMapping
    public ResponseEntity<List<DetalleFacturaDTO>> getAllDetallesFactura() {
        List<DetalleFacturaDTO> detallesFactura = detalleFacturaService.getAllDetallesFactura();
        return ResponseEntity.ok(detallesFactura);
    }

    @Operation(summary = "Obtener un Detalle de Factura por ID",
            description = "Devuelve los detalles completos de un Detalle de Factura específico usando su ID.")
    @ApiResponse(responseCode = "200", description = "Detalle de Factura encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleFacturaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Detalle de Factura no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<DetalleFacturaDTO> getDetalleFacturaById(@PathVariable Integer id) {
        try {
            DetalleFacturaDTO detalleFactura = detalleFacturaService.getDetalleFacturaById(id);
            return ResponseEntity.ok(detalleFactura);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Crear un nuevo Detalle de Factura",
            description = "Crea un nuevo Detalle de Factura con los datos proporcionados en el cuerpo de la solicitud.")
    @ApiResponse(responseCode = "201", description = "Detalle de Factura creado con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleFacturaDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos para la creación del Detalle de Factura")
    @PostMapping("/save")
    public ResponseEntity<DetalleFactura> saveDetalleFactura(@RequestBody DetalleFacturaDTO detalleFacturaDTO) {
        DetalleFactura savedDetalleFactura = detalleFacturaService.saveDetalleFactura(detalleFacturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDetalleFactura);
    }

    @Operation(summary = "Actualizar un Detalle de Factura existente",
            description = "Actualiza un Detalle de Factura usando su ID y los nuevos valores proporcionados en el cuerpo de la solicitud.")
    @ApiResponse(responseCode = "200", description = "Detalle de Factura actualizado con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalleFacturaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Detalle de Factura no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<DetalleFacturaDTO> updateDetalleFactura(@PathVariable Integer id, @RequestBody DetalleFacturaDTO detalleFacturaDTO) {
        try {
            DetalleFacturaDTO updatedDetalleFactura = detalleFacturaService.updateDetalleFactura(id, detalleFacturaDTO);
            return ResponseEntity.ok(updatedDetalleFactura);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary = "Obtener detalles de factura por folio",
            description = "Devuelve todos los detalles de factura asociados a un folio de factura.")
    @ApiResponse(responseCode = "200", description = "Detalles de factura encontrados")
    @ApiResponse(responseCode = "404", description = "No se encontraron detalles de factura para el folio dado")
    @GetMapping("/factura/{folio}")
    public ResponseEntity<List<DetalleFacturaDTO>> getDetallesByFacturaId(@PathVariable Integer folio) {
        List<DetalleFacturaDTO> detalles = detalleFacturaService.getDetallesByFacturaId(folio);
        if (detalles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalles);
    }
}
