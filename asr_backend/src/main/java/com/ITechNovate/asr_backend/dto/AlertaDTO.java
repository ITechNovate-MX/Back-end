package com.ITechNovate.asr_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlertaDTO {
    Integer id;
    Integer facturaId;
    String cliente;
    LocalDate fechaAlerta;
    Boolean correoEnviado;
    String detalles;
}
