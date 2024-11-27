package com.ITechNovate.asr_backend.dto;


import lombok.Data;
import java.util.Date;

@Data
public class DetalleFacturaDTO {
    private Integer facturaId;
    private Date fechaEntrega;
    private Date fechaVencimiento;
    private String estatus;
    private Integer credito;
    private Date fechaPortal;

    public void setId(Integer id) {
    }
}
