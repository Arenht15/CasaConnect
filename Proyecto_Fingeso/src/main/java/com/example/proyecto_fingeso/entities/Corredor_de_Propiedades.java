package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// @Table(name = "corredorDePropiedades")
@DiscriminatorValue("corredorDePropiedades")
// @Data
// @NoArgsConstructor
public class Corredor_de_Propiedades extends Usuario{

}
