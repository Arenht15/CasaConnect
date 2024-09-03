package com.example.proyecto_fingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.proyecto_fingeso.repository.InterVivienda;
import com.example.proyecto_fingeso.entities.Usuario;
import com.example.proyecto_fingeso.entities.Vivienda;


import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ViviendaService {

    @Autowired
    InterVivienda interVivienda;

    //obtiene todos los usuarios
    public ArrayList<Vivienda> getVivienda(){
        return (ArrayList<Vivienda>) interVivienda.findAll();
    }

    //Guarda la vivienda en la base de datos
    public Vivienda saveVivienda(Vivienda vivienda) {
        return interVivienda.save(vivienda);
    }

    // Encuentra una vivienda por su ID
    public Optional<Vivienda> findById(Long id) {
        return interVivienda.findById(id);
    }

    // Edita una vivienda existente
    public Vivienda editVivienda(Long id, Vivienda viviendaDetails) {
        return interVivienda.findById(id)
                .map(vivienda -> {
                    vivienda.setTipoVivienda(viviendaDetails.getTipoVivienda());
                    vivienda.setUbicacion(viviendaDetails.getUbicacion());
                    vivienda.setNumeroDeHabitaciones(viviendaDetails.getNumeroDeHabitaciones());
                    vivienda.setPrecio(viviendaDetails.getPrecio());
                    vivienda.setIntencionVenta(viviendaDetails.getIntencionVenta());
                    vivienda.setDescripcion(viviendaDetails.getDescripcion());
                    vivienda.setFotos(viviendaDetails.getFotos());
                    return interVivienda.save(vivienda);
                })
                .orElseGet(() -> {
                    viviendaDetails.setIdVivienda(id);
                    return interVivienda.save(viviendaDetails);
                });
    }

    public Vivienda getViviendaByCodigo(String codigo){return interVivienda.findViviendaByCodigo(codigo);}

    public boolean deleteVivienda(Long id) throws Exception {
        try{
            interVivienda.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    // Método para obtener las viviendas ordenadas por precio de mayor a menor
    public ArrayList<Vivienda> getViviendaOrder() {
        // Obtiene todas las viviendas desde la base de datos
        List<Vivienda> viviendas = interVivienda.findAll();

        // Ordena las viviendas por precio de mayor a menor
        List<Vivienda> viviendasOrdenadas = viviendas.stream()
                .sorted(Comparator.comparingDouble(Vivienda::getPrecio).reversed())
                .collect(Collectors.toList());

        // Retorna la lista ordenada como ArrayList
        return new ArrayList<>(viviendasOrdenadas);
    }

    public ArrayList<Vivienda> getViviendaOrderMenorMayor() {

        List<Vivienda> viviendas = interVivienda.findAll();

        List<Vivienda> viviendasOrdenadas = viviendas.stream()
                .sorted(Comparator.comparingDouble(Vivienda::getPrecio))
                .collect(Collectors.toList());

        return new ArrayList<>(viviendasOrdenadas);
    }

    public List<Vivienda> getFilteredViviendas(String tipoPropiedad, Double precioMin, Double precioMax, Integer numeroHabitaciones) {
        // Imprime los parámetros recibidos
        System.out.println("Tipo de Propiedad: " + tipoPropiedad);
        System.out.println("Precio Mínimo: " + precioMin);
        System.out.println("Precio Máximo: " + precioMax);
        System.out.println("Número de Habitaciones: " + numeroHabitaciones);

        List<Vivienda> viviendas = interVivienda.findAll();

        return viviendas.stream()
                .filter(v -> tipoPropiedad == null || v.getTipoVivienda().equalsIgnoreCase(tipoPropiedad))
                .filter(v -> precioMin == null || v.getPrecio() >= precioMin)
                .filter(v -> precioMax == null || v.getPrecio() <= precioMax)
                .filter(v -> numeroHabitaciones == null || v.getNumeroDeHabitaciones() == numeroHabitaciones || (numeroHabitaciones == 5 && v.getNumeroDeHabitaciones() >= 5))
                .collect(Collectors.toList());
    }

    public String guardarImagen(Long id, MultipartFile file) throws IOException {
        Vivienda vivienda = interVivienda.findByViviendaId(id);
        String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Crear la carpeta uploads si no existe
        Path uploadsPath = Paths.get("uploads");
        if (!Files.exists(uploadsPath)) {
            Files.createDirectories(uploadsPath);
        }

        Path rutaCompleta = uploadsPath.resolve(nombreArchivo);
        Files.copy(file.getInputStream(), rutaCompleta);

        List<String> fotos = vivienda.getFotos();
        fotos.add(nombreArchivo);
        vivienda.setFotos(fotos);
        interVivienda.save(vivienda);
        return nombreArchivo;
    }

}
