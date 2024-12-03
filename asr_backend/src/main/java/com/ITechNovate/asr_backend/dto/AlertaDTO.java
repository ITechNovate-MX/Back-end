package com.ITechNovate.asr_backend.dto;

import java.util.Date;


public class AlertaDTO {
    Integer facturaId;
    String cliente;
    Date fechaAlerta;
    Boolean correoEnviado;
    Integer usuarioId;

    public AlertaDTO(Integer facturaId, String cliente, Date fechaAlerta, Boolean correoEnviado, Integer usuarioId) {
        this.facturaId = facturaId;
        this.cliente = cliente;
        this.fechaAlerta = fechaAlerta;
        this.correoEnviado = correoEnviado;
        this.usuarioId = usuarioId;
    }

    public AlertaDTO(Integer facturaId, Date fechaAlerta, Integer usuarioId, Boolean correoEnviado) {
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public Boolean getCorreoEnviado() {
        return correoEnviado;
    }

    public void setCorreoEnviado(Boolean correoEnviado) {
        this.correoEnviado = correoEnviado;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
