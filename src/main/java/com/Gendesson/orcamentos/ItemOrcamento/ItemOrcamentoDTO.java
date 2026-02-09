package com.Gendesson.orcamentos.ItemOrcamento;

import java.math.BigDecimal;

public class ItemOrcamentoDTO {
    private Long id;
    private Long servicoId;
    private String servicoNome;
    private BigDecimal quantidade;
    private BigDecimal precoUnit;
    private BigDecimal subtotal;

    public ItemOrcamentoDTO() {
    }

    public ItemOrcamentoDTO(Long id, Long servicoId, String servicoNome, BigDecimal quantidade, BigDecimal precoUnit, BigDecimal subtotal) {
        this.id = id;
        this.servicoId = servicoId;
        this.servicoNome = servicoNome;
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

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public String getServicoNome() {
        return servicoNome;
    }

    public void setServicoNome(String servicoNome) {
        this.servicoNome = servicoNome;
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
