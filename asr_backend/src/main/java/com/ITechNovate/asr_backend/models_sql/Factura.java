package com.ITechNovate.asr_backend.models_sql;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @Column(name = "folio")
    private Integer folio;

    @Column(name = "fecha_emision", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    @Column(name = "cliente", length = 100, nullable = false)
    private String cliente;

    @Column(name = "orden_compra", length = 50)
    private String ordenCompra;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Column(name = "total", precision = 10, scale = 3, nullable = false)
    private BigDecimal total;

    @Column(name = "metodo_pago", length = 3)
    private String metodoPago;

    @Column(name = "archivo_xml")
    private String archivoXml;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalleFactura;

    // Constructores
    public Factura() {
    }

    public Factura(Integer folio, Date fechaEmision, String cliente, String ordenCompra, BigDecimal subtotal, BigDecimal total, String metodoPago, String archivoXml, List<DetalleFactura> detalleFactura) {
        this.folio = folio;
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.ordenCompra = ordenCompra;
        this.subtotal = subtotal;
        this.total = total;
        this.metodoPago = metodoPago;
        this.archivoXml = archivoXml;
        this.detalleFactura = detalleFactura;
    }

    // Getters y Setters
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

    public List<DetalleFactura> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(List<DetalleFactura> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public void addDetalleFactura(DetalleFactura detalle) {
        this.detalleFactura.add(detalle);
        detalle.setFactura(this);
    }

    public void removeDetalleFactura(DetalleFactura detalle) {
        this.detalleFactura.remove(detalle);
        detalle.setFactura(null);
    }

    public Integer getId() {
        return folio;
    }

    public void setId(Integer facturaId) {
    }
}