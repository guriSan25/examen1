package com.examen.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.examen.app.entity.Barco;
import com.examen.app.entity.Contenedor;
import com.examen.app.service.BarcoService;
import com.examen.app.service.ContenedorService;

@Controller
@RequestMapping("/contenedores")
public class ContenedorController {

    @Autowired
    private ContenedorService contenedorService;

    @Autowired
    private BarcoService barcoService;

    @GetMapping({ "", "/" })
    public String listar(Model model) {
        model.addAttribute("contenedores", contenedorService.listaContenedor());
        return "contenedores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("contenedor", new Contenedor());
        model.addAttribute("barcos", barcoService.list());
        return "crearContenedor";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Contenedor contenedor, RedirectAttributes redirectAttributes) {
        try {
            if (contenedor.getId_contenedor() == 0) {
                int idBarco = contenedor.getBarco().getId_barco();
                contenedor.setBarco(barcoService.get(idBarco));
                contenedorService.add(contenedor);
            } else {
                int idBarco = contenedor.getBarco().getId_barco();
                contenedor.setBarco(barcoService.get(idBarco));
                contenedorService.actualiContenedor(contenedor);
            }

            return "redirect:/contenedores";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/contenedores/nuevo";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Contenedor contenedor = contenedorService.consultar(id);
        List<Barco> barcos = barcoService.list();

        model.addAttribute("contenedor", contenedor); 
        model.addAttribute("barcos", barcos);
        return "editarContenedor"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        try {
            contenedorService.eliminar(id);
        } catch (IllegalArgumentException e) {
            return "redirect:/contenedores?error=" + e.getMessage();
        }
        return "redirect:/contenedores";
    }
}
