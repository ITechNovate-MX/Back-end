package com.ITechNovate.asr_backend.models_sql;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alerta_credito")
public class AlertaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id")
    private Integer facturaId;

    @Column(name = "fecha_alerta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAlerta;

    @Column(name = "factura_cliente", nullable = false)
    private Integer facturaCliente;

    @Column(name = "correo_enviado", nullable = false)
    private Boolean correoEnviado;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    // Relación con Factura (Muchos a Uno)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id", insertable = false, updatable = false)
    private Factura factura;

    // Relación con Usuario (Muchos a Uno)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private Usuario usuario;

    public AlertaCredito(Integer facturaId, Date fechaAlerta, Integer facturaCliente, Boolean correoEnviado, Integer usuarioId, Factura factura, Usuario usuario) {
        this.facturaId = facturaId;
        this.fechaAlerta = fechaAlerta;
        this.facturaCliente = facturaCliente;
        this.correoEnviado = correoEnviado;
        this.usuarioId = usuarioId;
        this.factura = factura;
        this.usuario = usuario;
    }

    public AlertaCredito() {
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public Integer getFacturaCliente() {
        return facturaCliente;
    }

    public void setFacturaCliente(Integer facturaCliente) {
        this.facturaCliente = facturaCliente;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}