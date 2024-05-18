package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vivienda")
@Data
@NoArgsConstructor
public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idAgenda;

    @Column(nullable = false)
    private String tipoVivienda;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private int numeroDeHabitaciones;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false)
    private int estado;

    @Column(nullable = false)
    private int intencionVenta;

    @Column(nullable = false)
    private String descripcion;

}
