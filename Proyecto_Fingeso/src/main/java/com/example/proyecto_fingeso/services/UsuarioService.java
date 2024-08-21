package com.example.proyecto_fingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.proyecto_fingeso.repository.InterUsuario;

import com.example.proyecto_fingeso.entities.Comprador;
import com.example.proyecto_fingeso.entities.Usuario;
import com.example.proyecto_fingeso.entities.Vendedor;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UsuarioService {

    @Autowired
    InterUsuario interUsuario;

    //obtiene todos los usuarios
    public ArrayList<Usuario> getUsuario(){
        return (ArrayList<Usuario>) interUsuario.findAll();
    }

    public Usuario saveUsuario(Usuario usuario){
        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0) {
            Usuario userUpdate = getUsuarioById(usuario.getIdUsuario());
            userUpdate.setNombre(usuario.getNombre());
            userUpdate.setEmail(usuario.getEmail());
            if (!usuario.getImagenPerfil().isEmpty()) {
                userUpdate.setImagenPerfil(usuario.getImagenPerfil());
            }
            return interUsuario.save(userUpdate);
        }
        // return interUsuario.save(usuario);
        switch (usuario.getRol().toUpperCase()) {
            case "VENDEDOR":
                return interUsuario.save(new Vendedor(usuario));
            case "COMPRADOR":
                return interUsuario.save(new Comprador(usuario));
            // ... otros casos si los hay ...
            default:
                throw new IllegalArgumentException("Rol de usuario no válido");
        }
    }

    public Usuario getUsuarioById(Long id){return interUsuario.findByUsuarioId(id);}

    public Usuario updateCar(Usuario usuario) {
        return interUsuario.save(usuario);
    }

    public Usuario getUsuarioByEmailAndContrasena(String email, String contrasena){
        return interUsuario.findByEmailAndContrasena(email, contrasena);
    }

    public Long getUsuarioByUsuarioOrEmail(String usuario, String email){
        return interUsuario.findByNombreOrEmail(usuario, email);
    }


    // @Value("${file.upload-dir}")
    // private String uploadDir;
    public String guardarImagenPerfil(Long id, MultipartFile file) throws IOException {
        Usuario usuario = getUsuarioById(id);
        String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Crear la carpeta uploads si no existe
        Path uploadsPath = Paths.get("uploads");
        if (!Files.exists(uploadsPath)) {
            Files.createDirectories(uploadsPath);
        }

        Path rutaCompleta = uploadsPath.resolve(nombreArchivo);
        Files.copy(file.getInputStream(), rutaCompleta);

        usuario.setImagenPerfil(nombreArchivo);
        interUsuario.save(usuario);
        return nombreArchivo;
    }
}
