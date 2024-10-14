package com.ITechNovate.asr_backend.models_sql;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "alerta_credito")
@Data // Anotacion de Lombok para generar los Getters y Setters y otros métodos automáticamente
@NoArgsConstructor
@AllArgsConstructor
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
}