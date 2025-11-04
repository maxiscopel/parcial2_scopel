package com.example.demo.controllers;

import com.example.demo.dto.CursoDTO;
import com.example.demo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        try {
            List<CursoDTO> lista = cursoService.listarCursos();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@RequestParam String nombre, @RequestParam Long profesorId) {
        try {
            CursoDTO dto = cursoService.crearCurso(nombre, profesorId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{cursoId}/estudiantes/{estudianteId}")
    public ResponseEntity<CursoDTO> asignarEstudiante(@PathVariable Long cursoId, @PathVariable Long estudianteId) {
        try {
            CursoDTO dto = cursoService.asignarEstudiante(cursoId, estudianteId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        try {
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
