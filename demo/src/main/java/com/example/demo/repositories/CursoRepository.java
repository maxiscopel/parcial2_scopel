package com.example.demo.repositories;

import com.example.demo.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // No hace falta nada más por ahora, JpaRepository ya tiene métodos CRUD
}
