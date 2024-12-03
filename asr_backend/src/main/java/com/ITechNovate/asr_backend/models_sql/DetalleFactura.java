package com.ITechNovate.asr_backend.models_sql;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id")
    private Integer facturaId;

    @Column(name = "fecha_entrega", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;

    @Column(name = "credito")
    private Integer credito;

    @Column(name = "fecha_portal")
    @Temporal(TemporalType.DATE)
    private Date fechaPortal;

    // Relación con Factura (Muchos a Uno)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id", insertable = false, updatable = false)
    private Factura factura;

    public DetalleFactura(Integer facturaId, Date fechaEntrega, Integer credito, Date fechaPortal, Factura factura) {
        this.facturaId = facturaId;
        this.fechaEntrega = fechaEntrega;
        this.credito = credito;
        this.fechaPortal = fechaPortal;
        this.factura = factura;
    }

    public DetalleFactura() {
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}