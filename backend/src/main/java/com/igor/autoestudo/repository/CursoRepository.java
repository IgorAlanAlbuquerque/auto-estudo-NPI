package com.igor.autoestudo.repository;

import com.igor.autoestudo.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Cursos, Long> {
    Cursos findByName(String name);
}
