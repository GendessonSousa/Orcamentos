package com.Gendesson.orcamentos.Clientes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clientes/ui")
public class ClienteControllerUI {
    private final ClienteService clienteService;

    public ClienteControllerUI(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar")
    public String listarClientes (Model model){
        List<ClienteDTO> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "clientes/listarClientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarClientePorId(@PathVariable Long id){
        clienteService.deletarClientes(id);
        return "redirect:/clientes/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarClientePorId(@PathVariable Long id, Model model){
        ClienteDTO cliente = clienteService.listarClientesPorId(id);
        if (cliente != null){
            model.addAttribute("cliente", cliente);
            return "clientes/detalhesCliente";
        } else {
            model.addAttribute("mensagem", "Cliente não encontrado!");
            return "clientes/listarClientes";
        }
    }

    @GetMapping("/adicionar")
    public String formularioAdicionarCliente (Model model){
        model.addAttribute("cliente", new ClienteDTO());
        return "clientes/adicionarCliente";
    }

    @PostMapping("/salvar")
    public String salvarCliente (@ModelAttribute ClienteDTO cliente, RedirectAttributes redirectAttributes){
        clienteService.criarCliente(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/clientes/ui/listar";
    }


    @GetMapping("/detalhes/{id}")
    public String detalhesCliente(@PathVariable Long id, Model model) {
        ClienteDTO cliente = clienteService.listarClientesPorId(id);

        if (cliente == null) {
            return "redirect:/clientes/ui/listar";
        }

        model.addAttribute("cliente", cliente);
        return "clientes/detalhesCliente";
    }


    @GetMapping("/alterar/{id}")
    public String alterarCliente(@PathVariable Long id, Model model) {
        ClienteDTO cliente = clienteService.listarClientesPorId(id);

        if (cliente == null) {
            return "redirect:/clientes/ui/listar";
        }

        model.addAttribute("cliente", cliente);
        return "clientes/alterarCliente";
    }

    @PostMapping("/alterar")
    public String salvarAlteracaoCliente(@ModelAttribute ClienteDTO cliente,
                                         RedirectAttributes redirectAttributes) {

        clienteService.atualizarClientes(cliente.getId(), cliente);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Cliente atualizado com sucesso!"
        );

        return "redirect:/clientes/ui/listar";
    }



}
