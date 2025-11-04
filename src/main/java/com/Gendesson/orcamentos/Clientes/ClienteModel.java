package com.Gendesson.orcamentos.Clientes;

import com.Gendesson.orcamentos.Orcamentos.OrcamentoModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_clientes")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String endereço;
    @OneToMany(mappedBy = "clienteModel")
    private List<OrcamentoModel> orcamentoModel;

    public ClienteModel() {
    }

    public ClienteModel(String nome, String email, String endereço) {
        this.nome = nome;
        this.email = email;
        this.endereço = endereço;
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

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }
}
