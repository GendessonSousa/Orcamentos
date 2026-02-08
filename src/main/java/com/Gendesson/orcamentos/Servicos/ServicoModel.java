package com.Gendesson.orcamentos.Servicos;

import com.Gendesson.orcamentos.Orcamentos.OrcamentoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_servicos")
public class ServicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String unidade;
    private BigDecimal precoUnit;
    @OneToMany(mappedBy = "servicoModel")
    @JsonIgnore
    private List<OrcamentoModel> orcamentos;

    public ServicoModel() {
    }

    public ServicoModel(String nome, String unidade, BigDecimal  precoUnit) {
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

    public BigDecimal  getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(BigDecimal  precoUnit) {
        this.precoUnit = precoUnit;
    }
}
