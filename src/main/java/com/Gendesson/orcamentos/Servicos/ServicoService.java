package com.Gendesson.orcamentos.Servicos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    public ServicoService(ServicoRepository servicoRepository, ServicoMapper servicoMapper) {
        this.servicoRepository = servicoRepository;
        this.servicoMapper = servicoMapper;
    }

    //Criar serviço (CREATE)
    public ServicoDTO criarServico (ServicoDTO servicoDTO){
        ServicoModel servico = servicoMapper.map(servicoDTO);
        servico = servicoRepository.save(servico);
        return servicoMapper.map(servico);
    }

    //Listar serviços (READ)
    public List<ServicoDTO> listarServicos (){
        return servicoRepository.findAll()
                .stream()
                .map(servicoMapper::map)
                .toList();
    }

    //Listar serviços por id (READ)
    public ServicoDTO listarServicosPorId (Long id){
        Optional<ServicoModel> servicoPorId = servicoRepository.findById(id);
        return servicoPorId.map(servicoMapper::map).orElse(null);
    }

    //Atualizar serviço (UPDATE)
    public ServicoDTO atualizarServico (ServicoDTO servicoDTO, Long id){
        Optional<ServicoModel> servicoExistente = servicoRepository.findById(id);
        if (servicoExistente.isPresent()){
            ServicoModel servicoAtualizado = servicoMapper.map(servicoDTO);
            servicoAtualizado.setId(id);
            ServicoModel servicoSalvo = servicoRepository.save(servicoAtualizado);
            return servicoMapper.map(servicoSalvo);
        }
        return null;
    }

    //Deletar cliente (DELETE)
    public boolean deletarServicos (Long id){
        if (!servicoRepository.existsById(id)){
            return false;
        }
        servicoRepository.deleteById(id);
        return true;
    }
}
