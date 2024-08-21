package com.example.proyecto_fingeso.controllers;

import com.example.proyecto_fingeso.entities.Usuario;
import com.example.proyecto_fingeso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.*;

@RestController
@RequestMapping("/api/v1/usuario")
// @CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioService serviceUsuario;

    //obtiene todos los usuarios
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listUser() {
        List<Usuario> usuario = serviceUsuario.getUsuario();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario) {
        Usuario usuarioNew = serviceUsuario.saveUsuario(usuario);
        return ResponseEntity.ok(usuarioNew);
    }

    @GetMapping("/{email}/{contrasena}")
    public ResponseEntity<Usuario> getUsuarioByEmailAndContrasena(@PathVariable String email, @PathVariable String contrasena){
        Usuario usuario = serviceUsuario.getUsuarioByEmailAndContrasena(email, contrasena);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/validate/{usuario}/{email}")
    public ResponseEntity<Long> getUsuarioByUsuarioOrEmail(@PathVariable String usuario, @PathVariable String email){
        Long vusuario = serviceUsuario.getUsuarioByUsuarioOrEmail(usuario, email);
        return ResponseEntity.ok(vusuario);
    }

    @PostMapping("/{id}/imagen")
    public ResponseEntity<String> subirImagenPerfil(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String nombreArchivo = serviceUsuario.guardarImagenPerfil(id, file);
            return ResponseEntity.ok(nombreArchivo);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        }
    }
}
