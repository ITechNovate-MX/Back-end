package com.ITechNovate.asr_backend.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FacturaDTO {
    Integer folio;
    Date fechaEmision;
    String moneda;
    String cliente;
    String ordenCompra;
    BigDecimal subtotal;
    BigDecimal total;
    String metodoPago;
    String archivoXml; // Nombre del archivo XML que el admin sube

    public FacturaDTO(Integer folio, Date fechaEmision, String cliente, String ordenCompra, BigDecimal subtotal, BigDecimal total, String metodoPago, String archivoXml, String moneda) {
        this.folio = folio;
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.ordenCompra = ordenCompra;
        this.subtotal = subtotal;
        this.total = total;
        this.metodoPago = metodoPago;
        this.archivoXml = archivoXml;
        this.moneda = moneda;
    }

    public FacturaDTO() {
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getArchivoXml() {
        return archivoXml;
    }

    public void setArchivoXml(String archivoXml) {
        this.archivoXml = archivoXml;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
