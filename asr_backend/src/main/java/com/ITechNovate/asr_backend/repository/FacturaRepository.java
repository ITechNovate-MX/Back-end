package com.ITechNovate.asr_backend.repository;

import com.ITechNovate.asr_backend.models_sql.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    // Buscar facturas por cliente (nombre parcial del cliente)
    List<Factura> findByClienteContaining(String cliente);

    // Buscar facturas emitidas dentro de un rango de fechas
    List<Factura> findByFechaEmisionBetween(Date fechaInicio, Date fechaFin);

    // Buscar facturas por cliente y monto total mayor a un valor
    List<Factura> findByClienteAndTotalGreaterThan(String cliente, BigDecimal total);

    // Buscar facturas con subtotal menor a un valor específico
    List<Factura> findBySubtotalLessThan(BigDecimal subtotal);

    // Consulta personalizada: obtener las facturas con total mayor a un valor
    @Query("SELECT f FROM Factura f WHERE f.total > :total")
    List<Factura> findFacturasWithTotalGreaterThan(BigDecimal total);

    // Consulta personalizada: obtener la factura más reciente por cliente
    @Query("SELECT f FROM Factura f WHERE f.cliente = :cliente ORDER BY f.fechaEmision DESC")
    Optional<Factura> findLatestFacturaByCliente(String cliente);

    // Procedimiento almacenado (si tienes uno en la base de datos)
    @Procedure("calculate_total_for_cliente")
    BigDecimal calculateTotalForCliente(String clienteId);
}