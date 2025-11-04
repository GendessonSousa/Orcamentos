package com.Gendesson.orcamentos.Servicos;

import com.Gendesson.orcamentos.Orcamentos.OrcamentoModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_servicos")
public class ServicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double quantidade;
    private String unidade;
    private Double precoUnit;
    @OneToMany(mappedBy = "servicoModel")
    private List<OrcamentoModel> orcamentoModel;

    public ServicoModel() {
    }

    public ServicoModel(String nome, Double quantidade, String unidade, Double precoUnit) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.precoUnit = precoUnit;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(Double precoUnit) {
        this.precoUnit = precoUnit;
    }
}
