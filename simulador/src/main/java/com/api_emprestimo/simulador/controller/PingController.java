package com.api_emprestimo.simulador.controller;

import com.api_emprestimo.simulador.service.SimulacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/ping")
public class PingController {
    @Autowired
    private SimulacaoService simulacaoService;
    @GetMapping
    public String pingPong() {
        return simulacaoService.RetornaPong();
    }

    
}
