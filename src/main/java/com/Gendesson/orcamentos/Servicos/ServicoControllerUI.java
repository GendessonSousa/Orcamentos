package com.Gendesson.orcamentos.Servicos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/servicos/ui")
public class ServicoControllerUI {
    private final ServicoService servicoService;

    public ServicoControllerUI(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping("/listar")
    public String listarServicos (Model model){
        List<ServicoDTO> servicos = servicoService.listarServicos();
        model.addAttribute("servicos", servicos);
        return "servicos/listarServicos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarServicoPorId(@PathVariable Long id){
        servicoService.deletarServicos(id);
        return "redirect:/servicos/ui/listar";
    }

    @GetMapping("/adicionar")
    public String formularioAdicionarServico (Model model){
        model.addAttribute("servico", new ServicoDTO());
        return "servicos/adicionarServico";
    }

    @PostMapping("/salvar")
    public String salvarServico (@ModelAttribute ServicoDTO servico, RedirectAttributes redirectAttributes){
        servicoService.criarServico(servico);
        redirectAttributes.addFlashAttribute("mensagem", "Serviço cadastrado com sucesso!");
        return "redirect:/servicos/ui/listar";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesServico(@PathVariable Long id, Model model) {
        ServicoDTO servico = servicoService.listarServicosPorId(id);

        if (servico == null) {
            return "redirect:/servicos/ui/listar";
        }

        model.addAttribute("servico", servico);
        return "servicos/detalhesServico";
    }

    @GetMapping("/alterar/{id}")
    public String alterarServico(@PathVariable Long id, Model model) {
        ServicoDTO servico = servicoService.listarServicosPorId(id);

        if (servico == null) {
            return "redirect:/servicos/ui/listar";
        }

        model.addAttribute("servico", servico);
        return "servicos/alterarServico";
    }

    @PostMapping("/alterar")
    public String salvarAlteracaoServico(@ModelAttribute ServicoDTO servico,
                                         RedirectAttributes redirectAttributes) {

        servicoService.atualizarServico(servico, servico.getId());

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Serviço atualizado com sucesso!"
        );

        return "redirect:/servicos/ui/listar";
    }
}
