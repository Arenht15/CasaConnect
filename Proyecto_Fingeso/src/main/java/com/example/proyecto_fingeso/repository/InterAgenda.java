package com.example.proyecto_fingeso.repository;

import com.example.proyecto_fingeso.entities.Agenda;
import com.example.proyecto_fingeso.entities.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterAgenda extends JpaRepository<Agenda, Long>{

    @Query(value = "SELECT * FROM agenda WHERE agenda.id = :id", nativeQuery = true)
    Agenda findByAgendaId(@Param("id") Long id);
}
