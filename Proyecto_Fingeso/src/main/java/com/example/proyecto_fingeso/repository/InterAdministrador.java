package com.example.proyecto_fingeso.repository;

import com.example.proyecto_fingeso.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterAdministrador extends JpaRepository<Administrador, Long> {

    @Query(value = "SELECT * FROM administrador WHERE administrador.id = :id", nativeQuery = true)
    Administrador findAdministradorById(@Param("id") Long id);

}
