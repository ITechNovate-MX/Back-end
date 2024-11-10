package com.ITechNovate.asr_backend.models_sql;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "usuario")
@Data // Anotacion de Lombok para generar los Getters y Setters y otros métodos automáticamente
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Getter
    @Column(name = "correo", length = 100, nullable = false)
    private String correo;

    @Getter
    @Column(name = "contraseña", length = 100, nullable = false)
    private String contrasena;

    @Getter
    @Column(name = "rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol ;

    public enum Rol {
        admin, invitado;

    }
}
