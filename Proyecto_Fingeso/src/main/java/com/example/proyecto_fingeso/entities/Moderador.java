package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moderador")
@Data
@NoArgsConstructor
public class Moderador extends Usuario{

}
