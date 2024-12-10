package com.ITechNovate.asr_backend.repository;

import com.ITechNovate.asr_backend.models_sql.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario (correo, contraseña, rol) VALUES (?1, SHA2(?2, 256), ?3)", nativeQuery = true)
    void registrarUsuarioConHash(String correo, String contrasena, String rol);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM usuario WHERE correo = ?1 AND contraseña = SHA2(?2, 256))", nativeQuery = true)
    Integer verificarCredenciales(String correo, String contrasena);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET correo = ?1, contraseña = SHA2(?2, 256), rol = ?3 WHERE id = ?4", nativeQuery = true)
    void actualizarUsuarioConHash(String correo, String contrasena, String rol, Integer id);


}