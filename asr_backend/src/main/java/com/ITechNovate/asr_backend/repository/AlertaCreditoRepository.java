package com.ITechNovate.asr_backend.repository;

import com.ITechNovate.asr_backend.models_sql.AlertaCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlertaCreditoRepository extends JpaRepository<AlertaCredito, Integer> {

    // Buscar todas las alertas para una factura específica
    List<AlertaCredito> findByFacturaId(Integer facturaId);

    // Buscar alertas por usuario
    List<AlertaCredito> findByUsuarioId(Integer usuarioId);

    // Buscar alertas cuyo correo aún no haya sido enviado
    List<AlertaCredito> findByCorreoEnviadoFalse();

    // Buscar alertas por rango de fechas
    List<AlertaCredito> findByFechaAlertaBetween(Date startDate, Date endDate);

    // Consultar si una alerta existe para una factura específica
    Optional<AlertaCredito> findByFacturaIdAndUsuarioId(Integer facturaId, Integer usuarioId);

    // Consulta personalizada: Obtener todas las alertas con correo no enviado y fecha de alerta anterior a hoy
    @Query("SELECT a FROM AlertaCredito a WHERE a.correoEnviado = false AND a.fechaAlerta < CURRENT_DATE")
    List<AlertaCredito> findPendingAlerts();

}
