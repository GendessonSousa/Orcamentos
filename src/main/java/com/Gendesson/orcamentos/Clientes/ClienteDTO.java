package com.Gendesson.orcamentos.Clientes;

import com.Gendesson.orcamentos.Orcamentos.OrcamentoModel;

import java.util.List;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String endereco;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
