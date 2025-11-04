package com.example.demo.controllers;

import com.example.demo.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes() {
        try {
            List<EstudianteDTO> lista = estudianteService.listarEstudiantes();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody EstudianteDTO dto) {
        try {
            EstudianteDTO nuevo = estudianteService.crearEstudiante(dto);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{estudianteId}/cursos")
    public ResponseEntity<List<String>> cursosDeEstudiante(@PathVariable Long estudianteId) {
        try {
            List<String> cursos = estudianteService.cursosDeEstudiante(estudianteId);
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
