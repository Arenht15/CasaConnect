package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// @Table(name = "vendedor")
@DiscriminatorValue("vendedor")
// @Data
@NoArgsConstructor
public class Vendedor extends Usuario{

    public Vendedor(Usuario usuario) {
        super(usuario);
    }

}
