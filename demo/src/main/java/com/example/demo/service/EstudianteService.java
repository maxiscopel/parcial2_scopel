package com.example.demo.service;

import com.example.demo.dto.EstudianteDTO;
import java.util.List;

public interface EstudianteService {
    List<EstudianteDTO> listarEstudiantes();
    EstudianteDTO crearEstudiante(EstudianteDTO dto);
    List<String> cursosDeEstudiante(Long estudianteId);
}
