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
