package com.example.demo.controllers;

import com.example.demo.dtos.Cliente;
import com.example.demo.services.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ClienteController {

    private final ClienteService clienteService;


    public ClienteController(ClienteService clienteService) {

        this.clienteService = clienteService;
    }

    @GetMapping("/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<?> obtenerInformacionCliente(
            @PathVariable String tipoDocumento,
            @PathVariable String numeroDocumento) {

        try {
            Cliente cliente = clienteService.obtenerInformacionCliente(tipoDocumento, numeroDocumento);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
