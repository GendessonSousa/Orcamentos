package com.Gendesson.orcamentos.Servicos;

import java.math.BigDecimal;

public class ServicoDTO {
    private Long id;
    private String nome;
    private String unidade;
    private BigDecimal precoUnit;

    public ServicoDTO() {
    }

    public ServicoDTO(Long id, String nome, String unidade, BigDecimal precoUnit) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.precoUnit = precoUnit;
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

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigDecimal precoUnit) {
        this.precoUnit = precoUnit;
    }
}
