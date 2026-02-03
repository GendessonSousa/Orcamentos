package com.Gendesson.orcamentos.Clientes;

import com.Gendesson.orcamentos.Orcamentos.OrcamentoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_clientes")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    @Column(unique = true)
    private String email;
    private String endereco;
    @OneToMany(mappedBy = "clienteModel")
    @JsonIgnore
    private List<OrcamentoModel> orcamento;

    public ClienteModel() {
    }

    public ClienteModel(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public List<OrcamentoModel> getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(List<OrcamentoModel> orcamento) {
        this.orcamento = orcamento;
    }
}
