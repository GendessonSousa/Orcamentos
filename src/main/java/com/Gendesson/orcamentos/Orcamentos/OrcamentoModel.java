package com.Gendesson.orcamentos.Orcamentos;

import com.Gendesson.orcamentos.Clientes.ClienteModel;
import com.Gendesson.orcamentos.Servicos.ServicoModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_orcamentos")
public class OrcamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoModel servicoModel;

    public OrcamentoModel() {
    }

    public OrcamentoModel(ClienteModel clienteModel, ServicoModel servicoModel) {
        this.clienteModel = clienteModel;
        this.servicoModel = servicoModel;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public ServicoModel getServicoModel() {
        return servicoModel;
    }

    public void setServicoModel(ServicoModel servicoModel) {
        this.servicoModel = servicoModel;
    }
}
