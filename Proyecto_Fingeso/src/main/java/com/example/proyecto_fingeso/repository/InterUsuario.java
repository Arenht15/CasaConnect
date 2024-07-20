package com.example.proyecto_fingeso.repository;

import com.example.proyecto_fingeso.entities.Chat;
import com.example.proyecto_fingeso.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterUsuario extends JpaRepository<Usuario, Long>{

    @Query(value = "SELECT * FROM usuario WHERE usuario.id_usuario = :id", nativeQuery = true)
    Usuario findByUsuarioId(@Param("id") Long id);

    @Query(value = "SELECT * FROM usuario WHERE usuario.nombre = :nombre", nativeQuery = true)
    Usuario findByUsuarioNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT COUNT(*) as cuantos FROM usuario WHERE usuario.nombre = :nombre OR usuario.email = :email", nativeQuery = true)
    Long findByNombreOrEmail(@Param("nombre") String nombre, @Param("email") String email);

    Usuario findByEmailAndContrasena(String email, String contrasena);
}
