package com.example.demo.service;

import com.example.demo.dto.ProfesorDTO;
import java.util.List;

public interface ProfesorService {
    List<ProfesorDTO> listarProfesores();
    ProfesorDTO crearProfesor(ProfesorDTO dto);
}
