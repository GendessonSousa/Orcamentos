package com.Gendesson.orcamentos.Clientes;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> listarClientes (){
        return clienteRepository.findAll();
    }
}
