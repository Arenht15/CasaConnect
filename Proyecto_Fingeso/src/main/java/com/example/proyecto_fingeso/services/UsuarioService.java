package com.example.proyecto_fingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto_fingeso.repository.InterUsuario;
import com.example.proyecto_fingeso.entities.Usuario;

import java.util.*;

@Service
public class UsuarioService {

    @Autowired
    InterUsuario interUsuario;

    //obtiene todos los autos
    public ArrayList<Usuario> getUsuario(){
        return (ArrayList<Usuario>) interUsuario.findAll();
    }

    public Usuario saveUsuario(Usuario usuario){
        return interUsuario.save(usuario);
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
}
