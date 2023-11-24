package com.example.demo.controllers;

import com.example.demo.services.FactorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SaludoFactorialController {

    @Autowired
    private FactorialService factorialService;

    @GetMapping("/factorial/{numero}")
    public String factorial(@PathVariable int numero) {
        long result = factorialService.factorial(numero);
        return "{\"factorial\":\"" + result + "\"}";
    }
    @GetMapping("/hola")
    public String hola() {
        return "Hola, equipo de desarrollo!";
    }


}
