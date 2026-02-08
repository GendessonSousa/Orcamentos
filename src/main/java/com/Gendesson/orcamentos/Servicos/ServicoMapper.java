package com.Gendesson.orcamentos.Servicos;

import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {
    public ServicoModel map(ServicoDTO servicoDTO){
        ServicoModel servicoModel = new ServicoModel();
        servicoModel.setId(servicoDTO.getId());
        servicoModel.setNome(servicoDTO.getNome());
        servicoModel.setPrecoUnit(servicoDTO.getPrecoUnit());
        servicoModel.setUnidade(servicoDTO.getUnidade());
        return servicoModel;
    }

    public ServicoDTO map(ServicoModel servicoModel){
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setId(servicoModel.getId());
        servicoDTO.setNome(servicoModel.getNome());
        servicoDTO.setPrecoUnit(servicoModel.getPrecoUnit());
        servicoDTO.setUnidade(servicoModel.getUnidade());
        return servicoDTO;
    }
}
