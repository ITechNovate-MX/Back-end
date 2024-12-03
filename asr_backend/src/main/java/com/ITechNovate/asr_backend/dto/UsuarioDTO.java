package com.ITechNovate.asr_backend.dto;

import com.ITechNovate.asr_backend.models_sql.Usuario.Rol;

public class UsuarioDTO {
    private String correo;
    private String contrasena;
    private Rol rol;

    public UsuarioDTO(String correo, String contrasena, Rol rol) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public UsuarioDTO() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

