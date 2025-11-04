package com.example.demo.service;

import com.example.demo.dto.CursoDTO;
import com.example.demo.entities.Curso;
import java.util.List;

public interface CursoService {
    List<CursoDTO> listarCursos();
    CursoDTO crearCurso(String nombre, Long profesorId);
    CursoDTO asignarEstudiante(Long cursoId, Long estudianteId);
    void eliminarCurso(Long id);
}
