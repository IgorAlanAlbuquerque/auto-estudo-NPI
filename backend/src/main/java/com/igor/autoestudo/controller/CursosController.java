package com.igor.autoestudo.controller;

import com.igor.autoestudo.DTO.CursosDTO;
import com.igor.autoestudo.entity.Cursos;
import com.igor.autoestudo.services.CursosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CursosController {
    private final CursosService cursosService;

    @Autowired
    public CursosController(CursosService cursosService) {
        this.cursosService = cursosService;
    }

    // GET /cursos: listar todos
    @GetMapping
    public List<Cursos> listarTodos(){
        return cursosService.listarTodos();
    }

    // POST /cursos: cadastrar novo curso
    @PostMapping
    public ResponseEntity<Cursos> cadastrar(@Valid @RequestBody CursosDTO cursosDTO){
        return ResponseEntity.ok(cursosService.cadastrar(cursosDTO.toEntity()));
    }

    // GET /cursos/{id}: busca um curso por id
    @GetMapping("/{id}")
    public ResponseEntity<Cursos> buscarPorId(@PathVariable Long id) {
        return cursosService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /cursos/{id} atualiza os dados de um curso
    @PutMapping("/{id}")
    public ResponseEntity<Cursos> atualizar(@PathVariable Long id, @Valid @RequestBody CursosDTO cursosDTO) {
        Cursos cursoAtualizado = cursosService.atualizar(id, cursosDTO.toEntity());
        return ResponseEntity.ok(cursoAtualizado);
    }

    // DELETE /cursos/{id}: Deleta um curso do sistema
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        cursosService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
