package com.ITechNovate.asr_backend.dto;

import java.math.BigDecimal;

public class FacturaDTO {
    Integer folio;
    String cliente;
    String ordenCompra;
    String noParte;
    String descripcion;
    BigDecimal cantidad;
    BigDecimal precioUnitario;
    BigDecimal subtotal;
    BigDecimal ival;
    BigDecimal total;
    String metodoPago;
    String archivoXml; // Nombre del archivo XML que el admin sube

    public FacturaDTO(Integer folio, String cliente, String ordenCompra, String noParte, String descripcion, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal subtotal, BigDecimal ival, BigDecimal total, String metodoPago, String archivoXml) {
        this.folio = folio;
        this.cliente = cliente;
        this.ordenCompra = ordenCompra;
        this.noParte = noParte;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.ival = ival;
        this.total = total;
        this.metodoPago = metodoPago;
        this.archivoXml = archivoXml;
    }

    public FacturaDTO(Integer folio, String cliente, BigDecimal cantidad, BigDecimal total, String archivoXml) {
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
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

    public String getNoParte() {
        return noParte;
    }

    public void setNoParte(String noParte) {
        this.noParte = noParte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIval() {
        return ival;
    }

    public void setIval(BigDecimal ival) {
        this.ival = ival;
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
}
