package com.ITechNovate.asr_backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    Integer folio;
    String cliente;
    String ordenCompra;
    BigDecimal subtotal;
    BigDecimal total;
    String metodoPago;
    String archivoXml; // Nombre del archivo XML que el admin sube

    public FacturaDTO(Integer folio, String cliente, BigDecimal subtotal, BigDecimal total, String archivoXml) {
    }
}
