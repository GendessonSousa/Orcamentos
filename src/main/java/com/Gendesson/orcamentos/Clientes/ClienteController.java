package com.Gendesson.orcamentos.Clientes;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar")
    public List<ClienteDTO> listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping("/criar")
    public ClienteDTO criarCliente(@RequestBody ClienteDTO cliente){
        return clienteService.criarCliente(cliente);
    }
}
