package com.example.demo.controllers;

import com.example.demo.dto.ProfesorDTO;
import com.example.demo.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listarProfesores() {
        try {
            List<ProfesorDTO> lista = profesorService.listarProfesores();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> crearProfesor(@RequestBody ProfesorDTO dto) {
        try {
            ProfesorDTO nuevo = profesorService.crearProfesor(dto);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
