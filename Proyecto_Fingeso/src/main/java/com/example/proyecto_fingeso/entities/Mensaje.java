package com.example.proyecto_fingeso.entities;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "mensaje")
@Data
@NoArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idMensaje;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private boolean visto;

    @Column(nullable = false)
    private Date hora;
}
