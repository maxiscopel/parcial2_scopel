package com.example.demo.dto;

import java.util.List;

public class CursoDTO {

    private Long id;
    private String nombre;
    private String profesorNombre;
    private List<String> estudiantesNombres;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProfesorNombre() { return profesorNombre; }
    public void setProfesorNombre(String profesorNombre) { this.profesorNombre = profesorNombre; }

    public List<String> getEstudiantesNombres() { return estudiantesNombres; }
    public void setEstudiantesNombres(List<String> estudiantesNombres) { this.estudiantesNombres = estudiantesNombres; }
}
