package com.ITechNovate.asr_backend.service;

import com.ITechNovate.asr_backend.dto.UsuarioDTO;
import com.ITechNovate.asr_backend.models_sql.Usuario;
import com.ITechNovate.asr_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void saveUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        if (usuarioDTO.getContrasena() == null || usuarioDTO.getContrasena().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (usuarioDTO.getRol() == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
        usuarioRepository.registrarUsuarioConHash(usuarioDTO.getCorreo(), usuarioDTO.getContrasena(), usuarioDTO.getRol().name());
    }

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void updateUsuario(Integer id, UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        if (usuarioDTO.getContrasena() == null || usuarioDTO.getContrasena().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (usuarioDTO.getRol() == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
        usuarioRepository.actualizarUsuarioConHash(usuarioDTO.getCorreo(), usuarioDTO.getContrasena(), usuarioDTO.getRol().name(), id);
    }

    public void deleteUsuarioById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    public boolean validarCredenciales(String correo, String contrasena) {
        Integer resultado = usuarioRepository.verificarCredenciales(correo, contrasena);
        return resultado != null && resultado == 1;
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getCorreo(),
                null,
                usuario.getRol()
        );
    }
}