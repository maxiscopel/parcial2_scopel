package com.example.demo;

import com.example.demo.entities.Curso;
import com.example.demo.entities.Estudiante;
import com.example.demo.entities.Profesor;
import com.example.demo.repositories.CursoRepository;
import com.example.demo.repositories.EstudianteRepository;
import com.example.demo.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private ProfesorRepository profesorRepo;

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @Override
    public void run(String... args) throws Exception {

        // Crear profesor
        Profesor prof = new Profesor();
        prof.setNombre("Juan lopez");
        prof.setEmail("juan@mail.com");
        profesorRepo.save(prof);

        // Crear estudiante
        Estudiante est = new Estudiante();
        est.setNombre("matias perez");
        est.setMatricula("12345");
        estudianteRepo.save(est);

        // Crear curso con profesor
        Curso curso = new Curso();
        curso.setNombre("Matemática");
        curso.setProfesor(prof);


        curso.setEstudiantes(new java.util.ArrayList<>());
        curso.getEstudiantes().add(est);

        cursoRepo.save(curso);

        // mostrar datos
        System.out.println("Profesor creado: " + prof);
        System.out.println("Estudiante creado: " + est);
        System.out.println("Curso creado: " + curso);
    }
}
