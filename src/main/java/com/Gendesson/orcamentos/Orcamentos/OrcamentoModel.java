package com.Gendesson.orcamentos.Orcamentos;

import com.Gendesson.orcamentos.Clientes.ClienteModel;
import com.Gendesson.orcamentos.ItemOrcamento.ItemOrcamentoModel;
import com.Gendesson.orcamentos.Servicos.ServicoModel;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_orcamentos")
public class OrcamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;
    @OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrcamentoModel> itens;

    private BigDecimal valorTotal;

    private LocalDate dataCriacao;

    private LocalDate dataValidade;


    public OrcamentoModel() {
    }

    public OrcamentoModel(Long id, ClienteModel cliente, List<ItemOrcamentoModel> itens, BigDecimal valorTotal, LocalDate dataCriacao, LocalDate dataValidade) {
        this.id = id;
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
        this.dataValidade = dataValidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public List<ItemOrcamentoModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemOrcamentoModel> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
