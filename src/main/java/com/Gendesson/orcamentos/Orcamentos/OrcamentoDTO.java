package com.Gendesson.orcamentos.Orcamentos;

import com.Gendesson.orcamentos.ItemOrcamento.ItemOrcamentoDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrcamentoDTO {
    private Long id;
    private Long clienteId;
    private String clienteNome;
    private List<ItemOrcamentoDTO> itens;
    private BigDecimal valorTotal;
    private LocalDate dataCriacao;
    private LocalDate dataValidade;

    public OrcamentoDTO() {
    }

    public OrcamentoDTO(Long id, Long clienteId, String clienteNome, List<ItemOrcamentoDTO> itens, BigDecimal valorTotal, LocalDate dataCriacao, LocalDate dataValidade) {
        this.id = id;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public List<ItemOrcamentoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemOrcamentoDTO> itens) {
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
