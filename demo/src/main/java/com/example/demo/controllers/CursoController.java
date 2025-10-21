package com.example.demo.controllers;

import com.example.demo.entities.Curso;
import com.example.demo.entities.Estudiante;
import com.example.demo.entities.Profesor;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.EstudianteRepository;
import com.example.demo.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private ProfesorRepository profesorRepo;

    @Autowired
    private EstudianteRepository estudianteRepo;

    // Listar todos los cursos
    @GetMapping
    public List<Curso> listarCursos() {
        return cursoRepo.findAll();
    }

    // Crear un curso nuevo con profesor
    @PostMapping
    public Curso crearCurso(@RequestParam String nombre, @RequestParam Long profesorId) {
        Profesor profesor = profesorRepo.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setProfesor(profesor);
        return cursoRepo.save(curso);
    }

    // Asignar estudiante a un curso
    @PostMapping("/{cursoId}/estudiantes/{estudianteId}")
    public Curso asignarEstudiante(@PathVariable Long cursoId, @PathVariable Long estudianteId) {
        Curso curso = cursoRepo.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (curso.getEstudiantes() == null) {
            curso.setEstudiantes(new java.util.ArrayList<>());
        }

        curso.getEstudiantes().add(estudiante);
        return cursoRepo.save(curso);
    }
}
