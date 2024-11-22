package com.ITechNovate.asr_backend.service;

import com.ITechNovate.asr_backend.dto.MaterialDTO;
import com.ITechNovate.asr_backend.models_sql.Material;
import com.ITechNovate.asr_backend.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    // Método para guardar un material
    public Material saveMaterial(MaterialDTO materialDTO, Long facturaId) {
        Material material = new Material();
        material.setNoParte(materialDTO.getNoParte());
        material.setDescripcion(materialDTO.getDescripcion());
        material.setCantidad(materialDTO.getCantidad());
        material.setPrecioUnitario(materialDTO.getPrecioUnitario());
        material.setImporte(materialDTO.getImporte());
        material.setIva(materialDTO.getIva());
        material.setFacturaId(facturaId); // Relacionar con la factura correspondiente

        return materialRepository.save(material);
    }
}
