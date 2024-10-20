package com.ITechNovate.asr_backend.models_sql;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data // Anotacion de Lombok para generar los Getters y Setters y otros métodos automáticamente
@NoArgsConstructor
@AllArgsConstructor
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


}
