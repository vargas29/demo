package com.example.demo.services.Implem;

import com.example.demo.dtos.Cliente;
import com.example.demo.services.ClienteService;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
@Service
public class ClienteServiceImpl implements ClienteService {
    @Override
    public Cliente obtenerInformacionCliente(String tipoDocumento, String numeroDocumento) {
        if (!tipoDocumento.equalsIgnoreCase("C") && !tipoDocumento.equalsIgnoreCase("P")) {
            throw new IllegalArgumentException("Tipo de documento no válido");
        }

        // Mock de datos del cliente (solo para Cedula de ciudadanía 23445322)
        if (tipoDocumento.equalsIgnoreCase("C") && numeroDocumento.equals("23445322")) {
            return  null;
        }

        throw new NoSuchElementException("Cliente no encontrado");
    }
}
