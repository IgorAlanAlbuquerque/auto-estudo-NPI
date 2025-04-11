package com.igor.autoestudo.controller;

import com.igor.autoestudo.DTO.AuthRequest;
import com.igor.autoestudo.security.JwtUtil;
import com.igor.autoestudo.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UsersService usersService;
    private final JwtUtil jwtUtil;

    public AuthController(UsersService usersService, JwtUtil jwtUtil) {
        this.usersService = usersService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        var user = usersService.autenticar(authRequest.getEmail(), authRequest.getSenha());

        return user.map(u -> {
            String token = jwtUtil.gerarToken(u.getEmail());
            return ResponseEntity.ok().body("Bearer " + token);
        }).orElseGet(() -> ResponseEntity.status(401).body("Credenciais inválidas"));
    }
}
