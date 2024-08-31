package com.example.proyecto_fingeso.controllers;

import com.example.proyecto_fingeso.entities.Vivienda;
import com.example.proyecto_fingeso.services.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/vivienda")
//@CrossOrigin("*")
public class ViviendaController {

    @Autowired
    ViviendaService serviceVivienda;

    //obtiene todas las casas
    @GetMapping("/")
    public ResponseEntity<List<Vivienda>> listHouse() {
        List<Vivienda> vivienda = serviceVivienda.getVivienda();
        return ResponseEntity.ok(vivienda);
    }

    //Guarda una vivienda en el sistema
    @PostMapping("/")
    public ResponseEntity<Vivienda> saveVivienda(@RequestBody Vivienda vivienda) {
        Vivienda viviendaNew = serviceVivienda.saveVivienda(vivienda);
        return ResponseEntity.ok(viviendaNew);
    }

    @GetMapping("/validate/{codigo}/")
    public ResponseEntity<Vivienda> getViviendaByCodigo(@PathVariable String codigo){
        Vivienda vivienda = serviceVivienda.getViviendaByCodigo(codigo);
        return ResponseEntity.ok(vivienda);
    }

    @DeleteMapping("/validate/{id}/")
    public ResponseEntity<Boolean> deleteViviendaById(@PathVariable Long id) throws Exception {
        var isDeleted = serviceVivienda.deleteVivienda(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ordenar/precio/MayoraMenor")
    public ResponseEntity<List<Vivienda>> listHouseOrder() {
        List<Vivienda> vivienda = serviceVivienda.getViviendaOrder();
        return ResponseEntity.ok(vivienda);
    }
}
