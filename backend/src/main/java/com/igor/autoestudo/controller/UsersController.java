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
        Users user = new Users();
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());
        user.setPassword(usersDTO.getPassword());
        user.setAtivo(usersDTO.getAtivo());

        Users userCriado = usersService.addNew(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCriado);
    }

    // GET /usuarios/{id}: buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> buscarPorId(@PathVariable Long id) {
        return usersService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*






PUT /usuarios/{id}: atualizar usuário

DELETE /usuarios/{id}: remover (se inativo)

POST /usuarios/{id}/curso/{cursoId}: associar usuário a um curso

POST /login: autenticação
     */
}
