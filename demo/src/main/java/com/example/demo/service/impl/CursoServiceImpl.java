package com.example.demo.service.impl;

import com.example.demo.dto.CursoDTO;
import com.example.demo.entities.Curso;
import com.example.demo.entities.Estudiante;
import com.example.demo.entities.Profesor;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.EstudianteRepository;
import com.example.demo.repositories.ProfesorRepository;
import com.example.demo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private ProfesorRepository profesorRepo;

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Override
    public List<CursoDTO> listarCursos() {
        List<Curso> cursos = cursoRepo.findAll();
        List<CursoDTO> lista = new ArrayList<>();
        for (Curso curso : cursos) {
            if (!curso.isActivo()) continue; // Borrado lógico
            CursoDTO dto = new CursoDTO();
            dto.setId(curso.getId());
            dto.setNombre(curso.getNombre());
            dto.setProfesorNombre(curso.getProfesor() != null ? curso.getProfesor().getNombre() : null);
            if (curso.getEstudiantes() != null) {
                List<String> nombres = new ArrayList<>();
                for (Estudiante e : curso.getEstudiantes()) {
                    if (e.isActivo()) nombres.add(e.getNombre());
                }
                dto.setEstudiantesNombres(nombres);
            }
            lista.add(dto);
        }
        return lista;
    }

    @Override
    public CursoDTO crearCurso(String nombre, Long profesorId) {
        Profesor profesor = profesorRepo.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setProfesor(profesor);
        cursoRepo.save(curso);

        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setProfesorNombre(profesor.getNombre());
        return dto;
    }

    @Override
    public CursoDTO asignarEstudiante(Long cursoId, Long estudianteId) {
        Curso curso = cursoRepo.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Estudiante estudiante = estudianteRepo.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if (curso.getEstudiantes() == null) curso.setEstudiantes(new ArrayList<>());
        curso.getEstudiantes().add(estudiante);
        cursoRepo.save(curso);

        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setProfesorNombre(curso.getProfesor() != null ? curso.getProfesor().getNombre() : null);
        if (curso.getEstudiantes() != null) {
            List<String> nombres = new ArrayList<>();
            for (Estudiante e : curso.getEstudiantes()) {
                if (e.isActivo()) nombres.add(e.getNombre());
            }
            dto.setEstudiantesNombres(nombres);
        }
        return dto;
    }

    @Override
    public void eliminarCurso(Long id) {
        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setActivo(false); // Borrado lógico
        cursoRepo.save(curso);
    }
}
