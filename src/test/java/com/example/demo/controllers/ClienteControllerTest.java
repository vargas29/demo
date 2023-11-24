package com.example.demo.controllers;

import com.example.demo.dtos.Cliente;
import com.example.demo.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class ClienteControllerTest {
    ClienteService clienteService = Mockito.mock(ClienteService.class);

    @Test
    public void testObtenerInformacionCliente_ClienteExistente() {
        // Mock de ClienteService
        ClienteService clienteService = Mockito.mock(ClienteService.class);

        // Crear un objeto Cliente con valores ficticios para simular un cliente existente
        Cliente clienteMock = new Cliente("Nombre", "SegundoNombre", "Apellido", "SegundoApellido", "123456789", "Direccion", "Ciudad"
        );

        // Establecer el comportamiento del ClienteService mockeado
        Mockito.when(clienteService.obtenerInformacionCliente(anyString(), anyString())).thenReturn(clienteMock);

        // Crear el controlador con el ClienteService mockeado
        ClienteController clienteController = new ClienteController(clienteService);

        // Ejecutar la prueba
        ResponseEntity<?> response = clienteController.obtenerInformacionCliente("tipoDocumento", "numeroDocumento");

        // Verificar que la respuesta sea exitosa y contenga los datos del cliente
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteMock, response.getBody());
    }

    @Test
    public void testObtenerInformacionCliente_ClienteNoEncontrado() {
        ClienteService clienteService = Mockito.mock(ClienteService.class);
        Mockito.when(clienteService.obtenerInformacionCliente(anyString(), anyString())).thenThrow(NoSuchElementException.class);

        ClienteController clienteController = new ClienteController(clienteService);

        ResponseEntity<?> response = clienteController.obtenerInformacionCliente("tipoDocumento", "numeroDocumento");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // Aquí podrías verificar algún mensaje de error específico si lo devuelves en el controlador
    }

    @Test
    public void testObtenerInformacionCliente_ParametrosInvalidos() {
        ClienteService clienteService = Mockito.mock(ClienteService.class);
        Mockito.when(clienteService.obtenerInformacionCliente(anyString(), anyString())).thenThrow(IllegalArgumentException.class);

        ClienteController clienteController = new ClienteController(clienteService);

        ResponseEntity<?> response = clienteController.obtenerInformacionCliente("tipoDocumento", "numeroDocumento");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // Aquí podrías verificar algún mensaje de error específico si lo devuelves en el controlador
    }


    @Test
    public void testObtenerInformacionCliente_ErrorInterno() {
        ClienteService clienteService = Mockito.mock(ClienteService.class);
        Mockito.when(clienteService.obtenerInformacionCliente(anyString(), anyString())).thenThrow(RuntimeException.class);

        ClienteController clienteController = new ClienteController(clienteService);

        ResponseEntity<?> response = clienteController.obtenerInformacionCliente("tipoDocumento", "numeroDocumento");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().toString().contains("Error interno"));
    }

}
