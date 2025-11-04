package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class EstudianteDTO {

    private Long id;
    private String nombre;
    private String matricula;
    private List<String> cursosNombres = new ArrayList<>();

    public EstudianteDTO() {}

    public EstudianteDTO(Long id, String nombre, String matricula, List<String> cursosNombres) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.cursosNombres = cursosNombres != null ? cursosNombres : new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public List<String> getCursosNombres() { return cursosNombres; }
    public void setCursosNombres(List<String> cursosNombres) {
        this.cursosNombres = cursosNombres != null ? cursosNombres : new ArrayList<>();
    }
}
