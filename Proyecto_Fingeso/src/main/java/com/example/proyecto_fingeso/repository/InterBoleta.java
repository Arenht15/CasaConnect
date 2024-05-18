package com.example.proyecto_fingeso.repository;

import com.example.proyecto_fingeso.entities.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InterBoleta extends JpaRepository<Boleta, Long>{

    @Query("SELECT q FROM Boleta q WHERE" + " q.condicion = ?1 ")
    public List<Boleta> findByCondicion(int condicion);

    @Query("SELECT l FROM Boleta l WHERE" + " l.idBoleta = ?1")
    public List<Boleta> findById_Boleta(Long idBoleta);

}
