package com.ITechNovate.asr_backend.controllers;

import com.ITechNovate.asr_backend.dto.UsuarioDTO;
import com.ITechNovate.asr_backend.repository.UsuarioRepository;
import com.ITechNovate.asr_backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Registro de usuario",
            description = "Ingreso de datos para almacenar nuevo usuario en la base de datos")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno al procesar la solicitud")
    @PostMapping("/registro")
    public ResponseEntity<String> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.saveUsuario(usuarioDTO);
            return ResponseEntity.ok("Usuario registrado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Listado de usuarios",
            description = "Listado de usuarios registrados en la base de datos")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno al procesar la solicitud")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Actualización de datos de usuario por ID",
            description = "Modificaciónn de datos de algún usuario ya registrado")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno al procesar la solicitud")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsuario(@RequestBody UsuarioDTO usuarioDTO, @PathVariable Integer id) {
        try {
            usuarioService.updateUsuario(id, usuarioDTO);
            return ResponseEntity.ok("Usuario actualizado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Borrado de usuario por ID",
            description = "Eliminación de registro de usuario usando su ID")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno al procesar la solicitud")
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO> deleteUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteUsuarioById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Validación de credenciales de usuario",
            description = "Confirmación de correo y contraseña correctos para un registro válido")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error interno al procesar la solicitud")
    @PostMapping("/validar")
    public ResponseEntity<String> validateUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        boolean esValido = usuarioService.validarCredenciales(usuarioDTO.getCorreo(), usuarioDTO.getContrasena());
        if (esValido) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}