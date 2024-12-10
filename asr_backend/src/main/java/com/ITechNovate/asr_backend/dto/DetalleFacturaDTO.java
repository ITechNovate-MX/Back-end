package com.ITechNovate.asr_backend.dto;

import java.util.Date;

public class DetalleFacturaDTO {
    private Integer facturaId;
    private Date fechaEntrega;
    private Date fechaVencimiento;
    private String estatus;
    private Integer credito;
    private Date fechaPortal;

    public DetalleFacturaDTO(Integer facturaId, Date fechaEntrega, Date fechaVencimiento, String estatus, Integer credito, Date fechaPortal) {
        this.facturaId = facturaId;
        this.fechaEntrega = fechaEntrega;
        this.fechaVencimiento = fechaVencimiento;
        this.estatus = estatus;
        this.credito = credito;
        this.fechaPortal = fechaPortal;
    }

    public DetalleFacturaDTO() {
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getCredito() {
        return credito;
    }

    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    public Date getFechaPortal() {
        return fechaPortal;
    }

    public void setFechaPortal(Date fechaPortal) {
        this.fechaPortal = fechaPortal;
    }

    public void setId(Integer id) {
    }
}
