package com.ITechNovate.asr_backend.models_sql;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "folio")
    private Integer folio;

    @Column(name= "fecha_emision", nullable = false)
    private Date fechaEmision;

    @Column(name= "cliente", length = 100, nullable = false)
    private String cliente;

    @Column(name= "orden_compra", length = 50)
    private String ordenCompra;

    @Column(name= "no_parte", length = 50)
    private String noParte;

    @Column(name= "descripcion", length = 200)
    private String descripcion;

    @Column(name= "cantidad", precision = 10, scale = 2, nullable = false)
    private BigDecimal cantidad;

    @Column(name= "precio_unitario", precision = 10, scale = 6, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "importe", precision = 10, scale = 2)
    private BigDecimal importe;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Column(name = "iva", precision = 10, scale = 2)
    private BigDecimal iva;

    @Column(name = "total", precision = 10, scale = 3, nullable = false)
    private BigDecimal total;

    @Column(name = "metodo_pago", length = 3)
    private String metodoPago;

    @Column(name = "archivo_xml")
    private String archivoXml;

    // Relacion con la tabla DetalleFactura
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalleFactura;

    public Factura(Integer folio, Date fechaEmision, String cliente, String ordenCompra, String noParte, String descripcion, BigDecimal cantidad, BigDecimal precioUnitario, BigDecimal importe, BigDecimal subtotal, BigDecimal iva, BigDecimal total, String metodoPago, String archivoXml, List<DetalleFactura> detalleFactura) {
        this.folio = folio;
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.ordenCompra = ordenCompra;
        this.noParte = noParte;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.metodoPago = metodoPago;
        this.archivoXml = archivoXml;
        this.detalleFactura = detalleFactura;
    }

    public Factura() {
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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
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
}
