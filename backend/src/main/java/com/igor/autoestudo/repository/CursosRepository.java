package com.igor.autoestudo.repository;

import com.igor.autoestudo.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursosRepository extends JpaRepository<Cursos, Long> {
    Optional<Cursos> findByName(String name);
}
