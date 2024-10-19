package com.ITechNovate.asr_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDTO {
    Integer facturaId;
    String cliente;
    Date fechaAlerta;
    Boolean correoEnviado;
    Integer usuarioId;

    public AlertaDTO(Integer facturaId, Date fechaAlerta, Integer usuarioId, Boolean correoEnviado) {
    }
}
