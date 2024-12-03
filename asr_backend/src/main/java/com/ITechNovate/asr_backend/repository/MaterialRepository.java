package com.ITechNovate.asr_backend.repository;

import com.ITechNovate.asr_backend.models_sql.Factura;
import com.ITechNovate.asr_backend.models_sql.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    // Método para encontrar materiales por factura
    List<Material> findByFactura(Factura factura);

    // Método para buscar un material por su "noParte" y su "factura"
    Optional<Material> findByNoParteAndFactura(String noParte, Factura factura);
}
