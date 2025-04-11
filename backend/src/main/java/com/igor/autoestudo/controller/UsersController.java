package com.igor.autoestudo.controller;

import com.igor.autoestudo.DTO.UsersDTO;
import com.igor.autoestudo.entity.Users;
import com.igor.autoestudo.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // GET /usuarios: listar todos
    @GetMapping
    public List<Users> listarTodos(){
        return usersService.findAll();
    }

    // POST /usuarios: cadastrar novo usuário
    @PostMapping
    public ResponseEntity<Users> criar(@Valid @RequestBody UsersDTO usersDTO) {
        Users userCriado = usersService.addNew(usersDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(userCriado);
    }

    // GET /usuarios/{id}: buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> buscarPorId(@PathVariable Long id) {
        return usersService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /usuarios/{id}: atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Users> atualizar(@PathVariable Long id, @Valid @RequestBody UsersDTO usersDTO) {
        Users userAtualizado =usersService.atualizar(id, usersDTO.toEntity());
        return ResponseEntity.ok(userAtualizado);
    }

    // DELETE /usuarios/{id}: remover (se inativo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        usersService.remover(id);
        return ResponseEntity.noContent().build();
    }

    // POST /usuarios/{id}/curso/{cursoId}: associar usuário a um curso
    @PostMapping("/{id}/curso/{cursoId}")
    public ResponseEntity<Users> associarCurso(@PathVariable Long id, @PathVariable Long cursoId) {
        return ResponseEntity.ok(usersService.associarCurso(id, cursoId));
    }
}
