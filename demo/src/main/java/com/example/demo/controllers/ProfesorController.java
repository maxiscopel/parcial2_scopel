package com.example.demo.controllers;

import com.example.demo.entities.Profesor;
import com.example.demo.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepo;

    // Listar todos los profesores
    @GetMapping
    public List<Profesor> listarProfesores() {
        return profesorRepo.findAll();
    }

    // Crear un profesor nuevo
    @PostMapping
    public Profesor crearProfesor(@RequestParam String nombre, @RequestParam String email) {
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setEmail(email);
        return profesorRepo.save(profesor);
    }
}
