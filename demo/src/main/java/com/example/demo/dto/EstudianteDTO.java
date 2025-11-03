package com.example.demo.dto;

import java.util.List;

public class EstudianteDTO {

    private Long id;
    private String nombre;
    private String matricula;
    private List<String> cursosNombres;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public List<String> getCursosNombres() { return cursosNombres; }
    public void setCursosNombres(List<String> cursosNombres) { this.cursosNombres = cursosNombres; }
}
