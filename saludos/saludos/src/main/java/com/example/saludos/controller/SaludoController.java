package com.example.saludos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.Period;

@Controller
public class SaludoController {

    // Al entrar a la raíz, muestra el formulario azul
    @GetMapping("/")
    public String mostrarFormulario() {
        return "saludo";
    }

    // Al procesar el formulario, calcula los datos y te envía a la vista verde
    @PostMapping("/bienvenida")
    public String saludar(
            @RequestParam("nombre") String nombre,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam("carrera") String carrera,
            @RequestParam("semestre") String semestre,
            @RequestParam("hobby") String hobby,
            Model model) {
        
        int edad = 0;
        try {
            LocalDate nacimiento = LocalDate.parse(fechaNacimiento);
            edad = Period.between(nacimiento, LocalDate.now()).getYears();
        } catch (Exception e) {
            edad = 0;
        }

        // Cargamos los datos calculados en el modelo
        model.addAttribute("nombre", nombre);
        model.addAttribute("fechaNacimiento", fechaNacimiento);
        model.addAttribute("edad", edad);
        model.addAttribute("carrera", carrera);
        model.addAttribute("semestre", semestre);
        model.addAttribute("hobby", hobby);

        // AQUÍ ESTÁ EL CAMBIO: Te envía a la pantalla verde independiente
        return "bienvenida";
    }
}
