package com.examen.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.app.entity.Contenedor;
import com.examen.app.repository.ContenedorRepository;

@Service
public class ContenedorService {

    @Autowired
    private ContenedorRepository contenedorRepository;

    public Contenedor add(Contenedor contenedor) {
        return contenedorRepository.save(contenedor);
    }

    public List<Contenedor> listaContenedor() {
        return contenedorRepository.findAll();
    }

    public Contenedor consultar(int id_contenedor){
        return contenedorRepository.findById(id_contenedor).orElse(null);
    }

    public void eliminar(int id_contenedor){
        contenedorRepository.deleteById(id_contenedor);
    }

    public Contenedor actualiContenedor(Contenedor contenedor){
        return contenedorRepository.save(contenedor);
    }
    

}
