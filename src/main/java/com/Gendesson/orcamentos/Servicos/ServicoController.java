package com.Gendesson.orcamentos.Servicos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    public final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    //Adicionar serviço (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarServico (@RequestBody ServicoDTO servico){
        ServicoDTO novoServico = servicoService.criarServico(servico);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Serviço criado com sucesso: " + novoServico.getNome());
    }

    //Listar serviços (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<ServicoDTO>> listarServicos(){
        List<ServicoDTO> servicos = servicoService.listarServicos();
        return ResponseEntity.ok(servicos);
    }

    //Listar serviços por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarServicosPorId(@PathVariable Long id){
        ServicoDTO servico = servicoService.listarServicosPorId(id);

        if (servico == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O cliente de ID " + id + " não foi encontrado!");
        }

        return ResponseEntity.ok(servico);
    }

    //Atualizar serviço (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarServicoPorId (@RequestBody ServicoDTO servicoAtualizado, @PathVariable Long id) {
        ServicoDTO servico = servicoService.atualizarServico(servicoAtualizado, id);

        if (servico == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O serviço de ID " + id + " não foi econtrado!");
        }

        return ResponseEntity.ok(
                "O serviço de ID " + id + " / " + servico.getNome() + " foi atualizado com sucesso!"
        );
    }

    //Deletar serviço (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarServicoPorId(@PathVariable Long id){
        boolean deletado = servicoService.deletarServicos(id);

        if (!deletado){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O serviço de ID " + id + " não foi encontrado!");
        }

        return ResponseEntity.ok("O serviço de ID " + id + " foi deletado com sucesso!");

    }

}
