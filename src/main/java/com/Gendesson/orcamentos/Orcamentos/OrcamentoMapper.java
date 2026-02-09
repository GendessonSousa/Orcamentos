package com.Gendesson.orcamentos.Orcamentos;

import com.Gendesson.orcamentos.Clientes.ClienteModel;
import com.Gendesson.orcamentos.ItemOrcamento.ItemOrcamentoDTO;
import com.Gendesson.orcamentos.ItemOrcamento.ItemOrcamentoMapper;
import com.Gendesson.orcamentos.ItemOrcamento.ItemOrcamentoModel;
import com.Gendesson.orcamentos.Servicos.ServicoModel;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrcamentoMapper {
    private final ItemOrcamentoMapper itemMapper;

    public OrcamentoMapper(ItemOrcamentoMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public OrcamentoModel map(OrcamentoDTO orcamentoDTO, ClienteModel clienteModel, List<ServicoModel> servicos){
        OrcamentoModel orcamentoModel = new OrcamentoModel();
        orcamentoModel.setId(orcamentoDTO.getId());
        orcamentoModel.setValorTotal(orcamentoDTO.getValorTotal());
        orcamentoModel.setDataCriacao(orcamentoDTO.getDataCriacao());
        orcamentoModel.setDataValidade(orcamentoDTO.getDataValidade());
        orcamentoModel.setCliente(clienteModel);

        // Itens
        if (orcamentoDTO.getItens() != null){
            List<ItemOrcamentoModel> itensModel = orcamentoDTO.getItens()
                    .stream()
                    .map(itemDTO -> {
                        ServicoModel servico = servicos.stream()
                                .filter(s -> s.getId().equals(itemDTO.getServicoId()))
                                .findFirst()
                                .orElseThrow(() ->
                                        new RuntimeException("Serviço não encontrado: " + itemDTO.getServicoId())
                                );
                        ItemOrcamentoModel item = itemMapper.map(itemDTO, servico);

                        item.setOrcamento(orcamentoModel);
                        return item;
                    }).toList();
            orcamentoModel.setItens(itensModel);
        }
        return orcamentoModel;
    }

    public OrcamentoDTO map(OrcamentoModel orcamentoModel){
        OrcamentoDTO orcamentoDTO = new OrcamentoDTO();
        orcamentoDTO.setId(orcamentoModel.getId());
        orcamentoDTO.setValorTotal(orcamentoModel.getValorTotal());
        orcamentoDTO.setDataCriacao(orcamentoModel.getDataCriacao());
        orcamentoDTO.setDataValidade(orcamentoModel.getDataValidade());

        // Cliente
        if (orcamentoModel.getCliente() != null){
            orcamentoDTO.setClienteId(orcamentoModel.getCliente().getId());
            orcamentoDTO.setClienteNome(orcamentoModel.getCliente().getNome());
        }

        // Itens
        if (orcamentoModel.getItens() != null){
            List<ItemOrcamentoDTO> itensDTO = orcamentoModel.getItens()
                    .stream()
                    .map(itemMapper::map)
                    .toList();

            orcamentoDTO.setItens(itensDTO);
        }

        return orcamentoDTO;
    }

}
