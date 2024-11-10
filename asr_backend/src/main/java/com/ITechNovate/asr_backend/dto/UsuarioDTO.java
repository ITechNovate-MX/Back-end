package com.ITechNovate.asr_backend.dto;

import com.ITechNovate.asr_backend.models_sql.Usuario.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String correo;
    private String contrasena;
    private Rol rol;
}