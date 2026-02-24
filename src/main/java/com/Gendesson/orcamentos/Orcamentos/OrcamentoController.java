package com.Gendesson.orcamentos.Orcamentos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {
    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    //CREATE
    @PostMapping("/criar")
    public ResponseEntity<String> criarOrcamento (@RequestBody OrcamentoDTO orcamento){
        OrcamentoDTO novoOrcamento = orcamentoService.criarOrcamento(orcamento);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Orçamento criado com sucesso!");
    }

    //READ
    @GetMapping("/listar")
    public ResponseEntity<List<OrcamentoDTO>> listarOrcamentos(){
        List<OrcamentoDTO> orcamentos = orcamentoService.listarOrcamentos();
        return ResponseEntity.ok(orcamentos);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarOrcamentosPorId(@PathVariable Long id){
        OrcamentoDTO orcamento = orcamentoService.listarOrcamentoPorID(id);

        if (orcamento == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O orçamento de ID " + id + " não foi encontrado");
        }

        return ResponseEntity.ok(orcamento);
    }




    //DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarOrcamento(@PathVariable Long id) {
        orcamentoService.deletarOrcamento(id);
        return ResponseEntity.noContent().build();
    }



}
