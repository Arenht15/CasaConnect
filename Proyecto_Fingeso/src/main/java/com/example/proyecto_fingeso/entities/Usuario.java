package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol", discriminatorType = DiscriminatorType.STRING)
public class Usuario {

    public Usuario(Usuario usuario) {
        this.setNombre(usuario.getNombre());
        this.setEmail(usuario.getEmail());
        this.setContrasena(usuario.getContrasena());
        this.setRol(usuario.getRol());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    // @Column(nullable = false)
    // private Integer rol;
    @Column(name = "rol", insertable = false, updatable = false)
    private String rol;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String email;

    //@Column(nullable = false)
    //private Chat chat;

    @Column(nullable = true)
    private String imagenPerfil;
}
