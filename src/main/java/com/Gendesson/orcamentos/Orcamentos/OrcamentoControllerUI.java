package com.Gendesson.orcamentos.Orcamentos;

import com.Gendesson.orcamentos.Clientes.ClienteDTO;
import com.Gendesson.orcamentos.Clientes.ClienteService;
import com.Gendesson.orcamentos.Servicos.ServicoDTO;
import com.Gendesson.orcamentos.Servicos.ServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orcamentos/ui")
public class OrcamentoControllerUI {
    private final OrcamentoService orcamentoService;
    private final ClienteService clienteService;
    private final ServicoService servicoService;

    public OrcamentoControllerUI(OrcamentoService orcamentoService, ClienteService clienteService, ServicoService servicoService) {
        this.orcamentoService = orcamentoService;
        this.clienteService = clienteService;
        this.servicoService = servicoService;
    }

    @GetMapping("/listar")
    public String listarOrcamentos (Model model){
        List<OrcamentoDTO> orcamentos = orcamentoService.listarOrcamentos();
        model.addAttribute("orcamentos", orcamentos);
        return "orcamentos/listarOrcamento";
    }

    @GetMapping("/deletar/{id}")
    public String deletarOrcamentoPorId(@PathVariable Long id){
        orcamentoService.deletarOrcamento(id);
        return "redirect:/orcamentos/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarOrcamentoPorId(@PathVariable Long id, Model model){
        OrcamentoDTO orcamento = orcamentoService.listarOrcamentoPorID(id);
        if (orcamento != null){
            model.addAttribute("orcamento", orcamento);
            return "orcamento/detalhesOrcamento";
        } else {
            model.addAttribute("mensagem", "Orçamento não encontrado!");
            return "orcamentos/listarOrcamento";
        }
    }

    @GetMapping("/adicionar")
    public String formularioAdicionarOrcamento (Model model){
        List<ClienteDTO> clientes = clienteService.listarClientes();
        List<ServicoDTO> servicos = servicoService.listarServicos();


        model.addAttribute("clientes", clientes);
        model.addAttribute("servicos", servicos);
        model.addAttribute("orcamento", new OrcamentoDTO());
        return "orcamentos/adicionarOrcamento";
    }

    @PostMapping("/salvar")
    public String salvarOrcamento (@ModelAttribute OrcamentoDTO orcamento, RedirectAttributes redirectAttributes){
        orcamentoService.criarOrcamento(orcamento);
        redirectAttributes.addFlashAttribute("mensagem", "Orçamento cadastrado com sucesso!");
        return "redirect:/orcamentos/ui/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesOrcamento(@PathVariable Long id, Model model) {
        OrcamentoDTO orcamento = orcamentoService.listarOrcamentoPorID(id);

        if (orcamento == null) {
            return "redirect:/orcamentos/ui/listar";
        }

        model.addAttribute("orcamento", orcamento);
        return "orcamentos/detalhesOrcamento";
    }

    @GetMapping("/alterar/{id}")
    public String alterarOrcamento(@PathVariable Long id, Model model) {
        OrcamentoDTO orcamento = orcamentoService.listarOrcamentoPorID(id);

        if (orcamento == null) {
            return "redirect:/orcamento/ui/listar";
        }

        model.addAttribute("orcamento", orcamento);
        return "orcamentos/alterarOrcamento";
    }
}
