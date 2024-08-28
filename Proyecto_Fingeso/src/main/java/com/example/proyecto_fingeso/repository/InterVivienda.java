package com.example.proyecto_fingeso.repository;

import com.example.proyecto_fingeso.entities.Usuario;
import com.example.proyecto_fingeso.entities.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterVivienda extends JpaRepository<Vivienda, Long> {

    @Query(value = "SELECT * FROM vivienda WHERE vivienda.id_vivienda = :id", nativeQuery = true)
    Vivienda findByViviendaId(@Param("id") Long id);

    @Query(value = "SELECT * FROM vivienda WHERE vivienda.codigo_unico = :codigo", nativeQuery = true)
    Vivienda findViviendaByCodigo(@Param("codigo") String codigo);
}
