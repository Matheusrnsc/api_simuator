package com.api_emprestimo.simulador.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.api_emprestimo.simulador.model.JsonModel;  

@RestController
@RequestMapping("/solicitacao")
public class JsonController {
    @PostMapping()
    public String postDadosBancarios (@RequestBody JsonModel entity) {
        String response = "Sua solicitação de empréstimo no valor de R$ " + entity.getValorDesejado() + " para o prazo de " + entity.getPrazo() + " meses foi recebida e está sendo processada."; 
        return response;
    }
    
}   
