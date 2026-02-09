package com.Gendesson.orcamentos.ItemOrcamento;

import com.Gendesson.orcamentos.Servicos.ServicoModel;
import org.springframework.stereotype.Component;

@Component
public class ItemOrcamentoMapper {
    /**
     * DTO → Model
     * Serviço deve ser injetado pelo Service
     */
    public ItemOrcamentoModel map(ItemOrcamentoDTO itemOrcamentoDTO, ServicoModel servicoModel){
        ItemOrcamentoModel itemOrcamentoModel = new ItemOrcamentoModel();
        itemOrcamentoModel.setId(itemOrcamentoDTO.getId());
        itemOrcamentoModel.setQuantidade(itemOrcamentoDTO.getQuantidade());
        itemOrcamentoModel.setPrecoUnit(itemOrcamentoDTO.getPrecoUnit());
        itemOrcamentoModel.setSubtotal(itemOrcamentoDTO.getSubtotal());
        itemOrcamentoModel.setServico(servicoModel);

        return itemOrcamentoModel;
    }


    public ItemOrcamentoDTO map(ItemOrcamentoModel itemOrcamentoModel){
        ItemOrcamentoDTO itemOrcamentoDTO = new ItemOrcamentoDTO();
        itemOrcamentoDTO.setId(itemOrcamentoModel.getId());
        itemOrcamentoDTO.setQuantidade(itemOrcamentoModel.getQuantidade());
        itemOrcamentoDTO.setPrecoUnit(itemOrcamentoModel.getPrecoUnit());
        itemOrcamentoDTO.setSubtotal(itemOrcamentoModel.getSubtotal());

        //Atribui os atributos de servicoId e servicoNome do DTO buscando do serviço atrelado ao model -> evita nullPointerException
        if (itemOrcamentoModel.getServico() != null){
            itemOrcamentoDTO.setServicoId(itemOrcamentoModel.getServico().getId());
            itemOrcamentoDTO.setServicoNome(itemOrcamentoModel.getServico().getNome());
        }
        return itemOrcamentoDTO;
    }


}
