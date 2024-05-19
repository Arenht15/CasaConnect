package com.example.proyecto_fingeso.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendedor")
@Data
@NoArgsConstructor
public class Vendedor extends Usuario{

}
