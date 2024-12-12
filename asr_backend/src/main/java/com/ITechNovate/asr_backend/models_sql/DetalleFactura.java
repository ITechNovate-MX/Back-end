package com.ITechNovate.asr_backend.models_sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "detallefactura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha_entrega", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;

    @Column(name = "fecha_vencimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    @Column(name = "estatus", nullable = false)
    @Enumerated(EnumType.STRING) // Si es un enum
    private Estatus estatus;

    public void setFacturaId(Integer facturaId) {
        this.factura = new Factura();
        factura.setId(facturaId);
    }

    public enum Estatus {
        en_progreso, pagada, vencida;
    }

    @Column(name = "credito")
    private Integer credito;

    @Column(name = "fecha_portal")
    @Temporal(TemporalType.DATE)
    private Date fechaPortal;

    // Relación con Factura (Muchos a Uno)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id", nullable = false)
    @JsonIgnore // Evita que la relación se serialice
    private Factura factura;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
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

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
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

    public Integer getFacturaId() {
        return factura != null ? factura.getId() : null;
    }
}