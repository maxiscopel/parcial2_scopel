package com.example.demo.controllers;

import com.example.demo.dto.EstudianteDTO;
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
    public List<EstudianteDTO> listarEstudiantes() {
        return estudianteRepo.findAll().stream().map(estudiante -> {
            EstudianteDTO dto = new EstudianteDTO();
            dto.setId(estudiante.getId());
            dto.setNombre(estudiante.getNombre());
            dto.setMatricula(estudiante.getMatricula());
            if (estudiante.getCursos() != null) {
                dto.setCursosNombres(
                        estudiante.getCursos().stream().map(Curso::getNombre).toList()
                );
            }
            return dto;
        }).toList();
    }


    @PostMapping
    public Estudiante crearEstudiante(@RequestBody EstudianteDTO dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setMatricula(dto.getMatricula());
        return estudianteRepo.save(estudiante);
    }


    @GetMapping("/{estudianteId}/cursos")
    public List<String> cursosDeEstudiante(@PathVariable Long estudianteId) {
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        if (estudiante.getCursos() == null) return List.of();
        return estudiante.getCursos().stream().map(Curso::getNombre).toList();
    }
}
