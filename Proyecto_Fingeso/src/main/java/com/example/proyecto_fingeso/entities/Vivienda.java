package com.example.proyecto_fingeso.entities;

//import org.hibernate.mapping.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vivienda")
@Data
@NoArgsConstructor
public class Vivienda{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idVivienda;

    @Column(nullable = false)
    private String codigoUnico;

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

    @ElementCollection
    @CollectionTable(name = "vivienda_fotos", joinColumns = @JoinColumn(name = "id_vivienda"))
    @Column(name = "url_foto")
    private List<String> fotos;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor;
}
