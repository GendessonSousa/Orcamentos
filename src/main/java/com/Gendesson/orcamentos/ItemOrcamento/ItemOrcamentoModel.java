package com.Gendesson.orcamentos.ItemOrcamento;

import com.Gendesson.orcamentos.Orcamentos.OrcamentoModel;
import com.Gendesson.orcamentos.Servicos.ServicoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_itens_orcamento")
public class ItemOrcamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    @JsonIgnore
    private OrcamentoModel orcamento;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    @JsonIgnore
    private ServicoModel servico;

    private BigDecimal quantidade;

    private BigDecimal precoUnit;

    private BigDecimal subtotal;

    public ItemOrcamentoModel() {
    }

    public ItemOrcamentoModel(Long id, OrcamentoModel orcamento, ServicoModel servico, BigDecimal quantidade, BigDecimal precoUnit, BigDecimal subtotal) {
        this.id = id;
        this.orcamento = orcamento;
        this.servico = servico;
        this.quantidade = quantidade;
        this.precoUnit = precoUnit;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrcamentoModel getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(OrcamentoModel orcamento) {
        this.orcamento = orcamento;
    }

    public ServicoModel getServico() {
        return servico;
    }

    public void setServico(ServicoModel servico) {
        this.servico = servico;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigDecimal precoUnit) {
        this.precoUnit = precoUnit;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
