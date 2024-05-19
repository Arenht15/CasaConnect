package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comprador")
@Data
@NoArgsConstructor
public class Comprador extends Usuario{

    //No tiene atributos ya que lo hereda de usuario
    //@Column(nullable = false)

}
