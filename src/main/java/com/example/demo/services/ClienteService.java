package com.example.demo.services;

import com.example.demo.dtos.Cliente;

public  interface ClienteService {
    Cliente obtenerInformacionCliente(String tipoDocumento, String numeroDocumento);
}
