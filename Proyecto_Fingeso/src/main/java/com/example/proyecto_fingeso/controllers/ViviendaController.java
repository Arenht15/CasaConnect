package com.example.proyecto_fingeso.controllers;

import com.example.proyecto_fingeso.entities.Vivienda;
import com.example.proyecto_fingeso.services.ViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.*;

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

    @GetMapping("/filtrar/")
    public List<Vivienda> getFilteredViviendas(
            @RequestParam(required = false) String tipoPropiedad,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(required = false) Integer numeroHabitaciones) {

        return serviceVivienda.getFilteredViviendas(tipoPropiedad, precioMin, precioMax, numeroHabitaciones);
    }

    //Guarda una vivienda en el sistema
    @PostMapping("/")
    public ResponseEntity<Vivienda> saveVivienda(@RequestBody Vivienda vivienda) {
        Vivienda viviendaNew = serviceVivienda.saveVivienda(vivienda);
        return ResponseEntity.ok(viviendaNew);
    }

    //Guarda una vivienda en el sistema
    @PutMapping("/{id}")
    public ResponseEntity<Vivienda> editVivienda(@PathVariable Long id, @RequestBody Vivienda vivienda) {
        Vivienda viviendaNew = serviceVivienda.editVivienda(id, vivienda);
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

    @GetMapping("/ordenar/precio/MenoraMayor")
    public ResponseEntity<List<Vivienda>> listHouseOrderMenorMayor() {
        List<Vivienda> vivienda = serviceVivienda.getViviendaOrderMenorMayor();
        return ResponseEntity.ok(vivienda);
    }

    @PostMapping("/{id}/imagen")
    public ResponseEntity<String> subirImagen(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String nombreArchivo = serviceVivienda.guardarImagen(id, file);
            return ResponseEntity.ok(nombreArchivo);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        }
    }

}
