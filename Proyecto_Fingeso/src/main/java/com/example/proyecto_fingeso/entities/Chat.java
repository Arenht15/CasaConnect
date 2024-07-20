package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "chat")
@Data
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idChat;

    @Column(nullable = false)
    private String nombreContacto;

    //@Column(nullable = false)
    //private String listaMensajes;

    @Column(nullable = false)
    private Date fecha;

}
