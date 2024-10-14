package com.ITechNovate.asr_backend.models_sql;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "detalle_factura")
@Data// Anotacion de Lombok para generar los Getters y Setters y otros métodos automáticamente
@NoArgsConstructor
@AllArgsConstructor
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

}