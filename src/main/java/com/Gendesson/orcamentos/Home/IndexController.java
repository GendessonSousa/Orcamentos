package com.Gendesson.orcamentos.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class IndexController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }
}
