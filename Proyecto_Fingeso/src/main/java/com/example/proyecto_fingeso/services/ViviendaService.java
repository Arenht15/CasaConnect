package com.example.proyecto_fingeso.services;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.stereotype.Service;

import com.example.proyecto_fingeso.repository.InterVivienda;

import com.example.proyecto_fingeso.entities.Vivienda;


import java.util.*;


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


}
