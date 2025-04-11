package com.igor.autoestudo.controller;

import com.igor.autoestudo.DTO.AuthRequest;
import com.igor.autoestudo.entity.Users;
import com.igor.autoestudo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class AuthController {
    private final UsersService usersService;

    @Autowired
    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    // POST /login: autenticação
    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody AuthRequest authRequest) {
        Optional<Users> userOptional = usersService.autenticar(authRequest.getEmail(), authRequest.getSenha());

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
        }
    }
}
