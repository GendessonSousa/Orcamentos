package com.Gendesson.orcamentos.Clientes;

import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }


    //Criar cliente (CREATE)
    public ClienteDTO criarCliente(ClienteDTO clienteDTO){
        ClienteModel cliente = clienteMapper.map(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.map(cliente);
    }

    //Listar clientes (READ)
    public List<ClienteDTO> listarClientes (){
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::map)
                .toList();
    }

    //Listar clientes por id (READ)
    public ClienteDTO listarClientesPorId (Long id){
        Optional<ClienteModel> clientePorId = clienteRepository.findById(id);
        return clientePorId.map(clienteMapper::map).orElse(null);
    }

    //Atualizar cliente (UPDATE)
    public ClienteDTO atualizarClientes(Long id, ClienteDTO clienteDTO){
        Optional<ClienteModel> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()){
            ClienteModel clienteAtualizado = clienteMapper.map(clienteDTO);
            clienteAtualizado.setId(id);
            ClienteModel clienteSalvo = clienteRepository.save(clienteAtualizado);
            return clienteMapper.map(clienteSalvo);
        }
        return null;
    }

    //Deletar cliente (DELETE)
    public boolean deletarClientes(Long id){
        if (!clienteRepository.existsById(id)){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }
}
