package com.Gendesson.orcamentos.Clientes;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteModel map(ClienteDTO clienteDTO){
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteDTO.getId());
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setEmail(clienteDTO.getEmail());
        clienteModel.setEndereco(clienteDTO.getEndereco());
        clienteModel.setTelefone(clienteDTO.getTelefone());
        return clienteModel;
    }

    public ClienteDTO map(ClienteModel clienteModel){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteModel.getId());
        clienteDTO.setNome(clienteModel.getNome());
        clienteDTO.setEmail(clienteModel.getEmail());
        clienteDTO.setEndereco(clienteModel.getEndereco());
        clienteDTO.setTelefone(clienteModel.getTelefone());
        return clienteDTO;
    }
}
