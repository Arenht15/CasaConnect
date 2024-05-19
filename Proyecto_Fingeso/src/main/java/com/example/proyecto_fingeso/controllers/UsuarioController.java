package com.example.proyecto_fingeso.controllers;

import com.example.proyecto_fingeso.entities.Usuario;
import com.example.proyecto_fingeso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioService serviceUsuario;

    //obtiene todos los usuarios
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> listCar() {
        List<Usuario> usuario = serviceUsuario.getUsuario();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> saveCar(@RequestBody Usuario usuario) {
        Usuario usuarioNew = serviceUsuario.saveUsuario(usuario);
        return ResponseEntity.ok(usuarioNew);
    }
}
