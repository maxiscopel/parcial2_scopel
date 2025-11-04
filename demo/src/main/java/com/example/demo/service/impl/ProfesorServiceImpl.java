package com.example.demo.service.impl;

import com.example.demo.dto.ProfesorDTO;
import com.example.demo.entities.Profesor;
import com.example.demo.repositories.ProfesorRepository;
import com.example.demo.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepo;

    @Override
    public List<ProfesorDTO> listarProfesores() {
        List<Profesor> profesores = profesorRepo.findAll();
        List<ProfesorDTO> lista = new ArrayList<>();
        for (Profesor p : profesores) {
            if (!p.isActivo()) continue; // borrado lógico
            ProfesorDTO dto = new ProfesorDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setEmail(p.getEmail());
            lista.add(dto);
        }
        return lista;
    }

    @Override
    public ProfesorDTO crearProfesor(ProfesorDTO dto) {
        Profesor profesor = new Profesor();
        profesor.setNombre(dto.getNombre());
        profesor.setEmail(dto.getEmail());
        profesorRepo.save(profesor);

        ProfesorDTO nuevo = new ProfesorDTO();
        nuevo.setId(profesor.getId());
        nuevo.setNombre(profesor.getNombre());
        nuevo.setEmail(profesor.getEmail());
        return nuevo;
    }
}
