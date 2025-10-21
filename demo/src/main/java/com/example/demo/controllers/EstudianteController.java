package com.example.demo.controllers;

import com.example.demo.entities.Curso;
import com.example.demo.entities.Estudiante;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private CursoRepository cursoRepo;

    // Listar todos los estudiantes
    @GetMapping
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepo.findAll();
    }

    // Listar los cursos de un estudiante
    @GetMapping("/{estudianteId}/cursos")
    public List<Curso> cursosDeEstudiante(@PathVariable Long estudianteId) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return estudiante.getCursos();
    }
}
