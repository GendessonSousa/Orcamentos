package com.Gendesson.orcamentos.Clientes;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ClienteController {
    @GetMapping("/testeInicial")
    public String testeInicial(){
        return "Primeira rota, teste incial da aplicação!";
    }
}
