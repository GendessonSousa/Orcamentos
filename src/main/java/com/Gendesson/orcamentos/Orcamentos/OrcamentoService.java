package com.Gendesson.orcamentos.Orcamentos;

import com.Gendesson.orcamentos.Clientes.ClienteModel;
import com.Gendesson.orcamentos.Clientes.ClienteRepository;
import com.Gendesson.orcamentos.ItemOrcamento.ItemOrcamentoModel;
import com.Gendesson.orcamentos.Servicos.ServicoModel;
import com.Gendesson.orcamentos.Servicos.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {
    private final OrcamentoRepository orcamentoRepository;
    private final OrcamentoMapper orcamentoMapper;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    public OrcamentoService(OrcamentoRepository orcamentoRepository, OrcamentoMapper orcamentoMapper, ClienteRepository clienteRepository, ServicoRepository servicoRepository) {
        this.orcamentoRepository = orcamentoRepository;
        this.orcamentoMapper = orcamentoMapper;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
    }

    //CREATE
    public OrcamentoDTO criarOrcamento (OrcamentoDTO orcamentoDTO){
        //Buscar cliente
        ClienteModel cliente = clienteRepository.findById(orcamentoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + orcamentoDTO.getClienteId()));

        //Buscar serviços
        List<Long> servicoItens = orcamentoDTO.getItens()
                .stream()
                .map(item -> item.getServicoId())
                .toList();

        List<ServicoModel> servicos = servicoRepository.findAllById(servicoItens);

        //Mapear DTO -> Model
        OrcamentoModel orcamento = orcamentoMapper.map(orcamentoDTO, cliente, servicos);

        //Calcular subtotal e total
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemOrcamentoModel item : orcamento.getItens()){
            BigDecimal subtotal = item.getPrecoUnit()
                    .multiply(item.getQuantidade());

            item.setSubtotal(subtotal);
            valorTotal = valorTotal.add(subtotal);
        }
        orcamento.setValorTotal(valorTotal);

        //Salvar
        OrcamentoModel salvo = orcamentoRepository.save(orcamento);

        //Retornar DTO
        return orcamentoMapper.map(salvo);
    }

    //READ
    public List<OrcamentoDTO> listarOrcamentos (){
        return orcamentoRepository.findAll()
                .stream()
                .map(orcamentoMapper::map)
                .toList();
    }

    //READ BY ID
    public OrcamentoDTO listarOrcamentoPorID(Long id){
        Optional<OrcamentoModel> orcamentoPorId = orcamentoRepository.findById(id);
        return orcamentoPorId.map(orcamentoMapper::map).orElse(null);
    }

    //DELETE
    public void deletarOrcamento(Long id) {
        OrcamentoModel orcamento = orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));

        orcamentoRepository.delete(orcamento);
    }

    //UPDATE
    @Transactional
    public OrcamentoDTO atualizarOrcamento(Long id, OrcamentoDTO orcamentoDTO){
        OrcamentoModel existente = orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado"));

        ClienteModel cliente = clienteRepository.findById(orcamentoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        existente.setCliente(cliente);
        existente.setDataValidade(orcamentoDTO.getDataValidade());

        //Limpa itens antigos
        existente.getItens().clear();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (var itemDTO : orcamentoDTO.getItens()) {
            System.out.println("ServicoId: " + itemDTO.getServicoId());
            ServicoModel servico = servicoRepository.findById(itemDTO.getServicoId())
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

            ItemOrcamentoModel item = new ItemOrcamentoModel();
            item.setOrcamento(existente);
            item.setServico(servico);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnit(itemDTO.getPrecoUnit());

            BigDecimal subtotal = item.getPrecoUnit()
                    .multiply(item.getQuantidade());

            item.setSubtotal(subtotal);

            valorTotal = valorTotal.add(subtotal);

            existente.getItens().add(item);
        }

        existente.setValorTotal(valorTotal);

        OrcamentoModel salvo = orcamentoRepository.save(existente);

        return orcamentoMapper.map(salvo);
    }
}
