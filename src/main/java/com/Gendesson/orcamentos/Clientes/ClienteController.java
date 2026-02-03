package com.Gendesson.orcamentos.Clientes;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }




    //Adicionar cliente (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarCliente (@RequestBody ClienteDTO cliente){
        ClienteDTO novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente criado com sucesso: " + novoCliente.getNome() + " - (ID): " + novoCliente.getId());
    }

    //Listar clientes (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    //Listar clientes por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarClientesPorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.listarClientesPorId(id);

        if (cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O cliente de ID " + id + " não foi encontrado" );
        }

        return ResponseEntity.ok(cliente);
    }

    //Atualizar cliente (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarClientePorId (@PathVariable Long id, @RequestBody ClienteDTO clienteAtualizado){
        ClienteDTO cliente = clienteService.atualizarClientes(id, clienteAtualizado);

        if (cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O cliente de ID " + id + " não foi encontrado!");
        }

        return ResponseEntity.ok(
                "O cliente de ID " + id + " / " + cliente.getNome() + " foi atualizado com sucesso!"
        );
    }

    //Deletar cliente (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarClientePorId(@PathVariable Long id){
        boolean deletado = clienteService.deletarClientes(id);

        if (!deletado){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O cliente de ID " + id + " não foi encontrado");
        }

        return ResponseEntity.ok("O cliente de ID " + id + " foi deletado com sucesso!");
    }
}
