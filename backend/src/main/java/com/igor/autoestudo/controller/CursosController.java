package com.igor.autoestudo.controller;

import com.igor.autoestudo.services.CursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CursosController {
    private final CursosService cursosService;

    @Autowired
    public CursosController(CursosService cursosService) {
        this.cursosService = cursosService;
    }
}
