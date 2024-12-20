package com.ITechNovate.asr_backend.repository;


import com.ITechNovate.asr_backend.models_sql.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
    // Método para buscar detalles de factura por el ID de la factura
    List<DetalleFactura> findByFactura_Id(Integer facturaId);
    Optional<DetalleFactura> findByFacturaFolio(Integer folio);
}