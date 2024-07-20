package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "boleta")
@Data
@NoArgsConstructor
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idBoleta;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private float monto;

    @Column(nullable = false)
    private int condicion;

    @Column(nullable = false)
    private Long idCorredorDePropiedades;
}
