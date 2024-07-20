package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "agenda")
@Data
@NoArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idAgenda;


    @Column(nullable = false)
    private Date diasAgendados;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String direccionVivienda;

    @Column(nullable = false)
    private String nombrePersona;
}
