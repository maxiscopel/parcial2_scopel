package com.example.demo.service.impl;

import com.example.demo.dto.EstudianteDTO;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Estudiante;
import com.example.demo.repositories.EstudianteRepository;
import com.example.demo.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Override
    public List<EstudianteDTO> listarEstudiantes() {
        List<EstudianteDTO> lista = new ArrayList<>();
        for (Estudiante e : estudianteRepo.findAll()) {
            if (!e.isActivo()) continue; // borrado lógico
            EstudianteDTO dto = new EstudianteDTO();
            dto.setId(e.getId());
            dto.setNombre(e.getNombre());
            dto.setMatricula(e.getMatricula());

            List<String> cursosNombres = new ArrayList<>();
            if (e.getCursos() != null) {
                for (Curso c : e.getCursos()) {
                    if (c.isActivo()) cursosNombres.add(c.getNombre());
                }
            }
            dto.setCursosNombres(cursosNombres);
            lista.add(dto);
        }
        return lista;
    }

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO dto) {
        Estudiante e = new Estudiante();
        e.setNombre(dto.getNombre());
        e.setMatricula(dto.getMatricula());
        estudianteRepo.save(e);

        EstudianteDTO nuevo = new EstudianteDTO();
        nuevo.setId(e.getId());
        nuevo.setNombre(e.getNombre());
        nuevo.setMatricula(e.getMatricula());
        return nuevo;
    }

    @Override
    public List<String> cursosDeEstudiante(Long estudianteId) {
        Estudiante e = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        List<String> nombres = new ArrayList<>();
        if (e.getCursos() != null) {
            for (Curso c : e.getCursos()) {
                if (c.isActivo()) nombres.add(c.getNombre());
            }
        }
        return nombres;
    }
}
