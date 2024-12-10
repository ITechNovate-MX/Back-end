package com.ITechNovate.asr_backend.dto;

import java.math.BigDecimal;

public class MaterialDTO {
    private String noParte;
    private String descripcion;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal importe;
    private BigDecimal iva;
    private String facturaId;
    private Integer facturaid;

    public MaterialDTO(String noParte, String descripcion, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal importe, BigDecimal iva, String facturaId, Integer facturaid) {
        this.noParte = noParte;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.iva = iva;
        this.facturaId = facturaId;
        this.facturaid = facturaid;
    }

    public MaterialDTO() {
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

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public String getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(String facturaId) {
        this.facturaId = facturaId;
    }

    public Integer getFacturaid() {
        return facturaid;
    }

    public void setFacturaid(Integer facturaid) {
        this.facturaid = facturaid;
    }
}
