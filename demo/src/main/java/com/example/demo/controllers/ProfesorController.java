package com.example.demo.controllers;

import com.example.demo.dto.ProfesorDTO;
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


    @GetMapping
    public List<ProfesorDTO> listarProfesores() {
        return profesorRepo.findAll().stream().map(profesor -> {
            ProfesorDTO dto = new ProfesorDTO();
            dto.setId(profesor.getId());
            dto.setNombre(profesor.getNombre());
            dto.setEmail(profesor.getEmail());
            return dto;
        }).toList();
    }


    @PostMapping
    public Profesor crearProfesor(@RequestBody ProfesorDTO dto) {
        Profesor profesor = new Profesor();
        profesor.setNombre(dto.getNombre());
        profesor.setEmail(dto.getEmail());
        return profesorRepo.save(profesor);
    }
}
