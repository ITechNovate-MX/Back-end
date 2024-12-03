package com.ITechNovate.asr_backend.models_sql;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Long id; // Clave primaria

    @Column(name = "no_parte", length = 50, nullable = false)
    private String noParte;

    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "cantidad", precision = 10, scale = 2, nullable = false)
    private BigDecimal cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 6, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "importe", precision = 10, scale = 2, nullable = false)
    private BigDecimal importe;

    @Column(name = "iva", precision = 10, scale = 2)
    private BigDecimal iva;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    public void setFacturaId(Long facturaId) {
    }
}

