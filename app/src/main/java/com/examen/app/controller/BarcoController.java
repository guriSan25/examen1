package com.examen.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.examen.app.entity.Barco;
import com.examen.app.repository.BarcoRepository;
import com.examen.app.service.BarcoService;

@Controller
@RequestMapping("/barcos")
public class BarcoController {
    
    @Autowired
    private BarcoService barcoService;
    @Autowired
    private BarcoRepository barcoRepository;

    // Método para listar todos los barcos
    @GetMapping
    public String listarBarcos(Model model) {
        // Se obtiene la lista de barcos del servicio
        model.addAttribute("barcos", barcoService.list());
        return "listarBarcos"; // Nombre de la vista donde mostrarás la lista de barcos
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("barcos", new Barco());
        return "crearBarco";
    }

    @PostMapping("/guardar")
    public String guardarBarco(@ModelAttribute("barcos") Barco barco){
        barcoRepository.save(barco);
        return "redirect:/barcos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Barco barco = barcoService.get(id);
        model.addAttribute("barcos", barco);
        barcoService.update(barco);
        return "editarBarco";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarBarco(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            barcoService.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Error", "Ya no se borró el barquito.");
            return "redirect:/barcos";
        }
        redirectAttributes.addFlashAttribute("Success", "Barco eliminado correctamente.");
        return "redirect:/barcos";
    }
}
