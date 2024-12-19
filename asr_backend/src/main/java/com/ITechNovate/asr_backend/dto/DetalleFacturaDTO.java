package com.ITechNovate.asr_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

public class DetalleFacturaDTO {
    private Integer facturaId;
    private OffsetDateTime fechaEntrega;
    private OffsetDateTime fechaVencimiento;
    private OffsetDateTime fechaPortal;
    private String estatus;
    private Integer credito;
    private Float tipoCambio;

    public DetalleFacturaDTO() {
    }

    public DetalleFacturaDTO(Integer facturaId, OffsetDateTime fechaEntrega, OffsetDateTime fechaVencimiento, OffsetDateTime fechaPortal, String estatus, Integer credito, Float tipoCambio) {
        this.facturaId = facturaId;
        this.fechaEntrega = fechaEntrega;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaPortal = fechaPortal;
        this.estatus = estatus;
        this.credito = credito;
        this.tipoCambio = tipoCambio;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public OffsetDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(OffsetDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public OffsetDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(OffsetDateTime fechaVencimiento) {
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

    public OffsetDateTime getFechaPortal() {
        return fechaPortal;
    }

    public void setFechaPortal(OffsetDateTime fechaPortal) {
        this.fechaPortal = fechaPortal;
    }

    public void setId(Integer id) {
    }
    public Float getTipoCambio(){ return tipoCambio; }
    public void setTipoCambio(Float TipoCambio){ this.tipoCambio = TipoCambio; }
}
