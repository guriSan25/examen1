package com.examen.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.app.entity.Barco;
import com.examen.app.repository.BarcoRepository;

@Service
public class BarcoService {
    

    @Autowired
    private BarcoRepository barcoRepository;

    public Barco add(Barco barco){
        return barcoRepository.save(barco);
    }

    public List<Barco> list(){
        return barcoRepository.findAll();
    }

    public Barco get(int id){
        return barcoRepository.findById(id).orElse(null);
    }
    
    public void deleteById(int id) {
        barcoRepository.deleteById(id);
    }

    public Barco update(Barco barco){
        return barcoRepository.save(barco);
    }
}
