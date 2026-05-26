package com.api_emprestimo.simulador.controller;
import com.api_emprestimo.simulador.dto.SimulacaoResponse;
import com.api_emprestimo.simulador.service.SimulacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.api_emprestimo.simulador.model.JsonModel;  


@RestController //Diz que essa classe responde requisições HTTP
@RequestMapping("/solicitacao") //Define o endpoint
public class JsonController {
    @Autowired //similar ao @Data lombook -> injeta o construtor ->substitui "... new SimulacaoService"
    private SimulacaoService simulacaoService;

   @PostMapping //Define que a classe simularJson é metodo post
    public SimulacaoResponse simularJson(@RequestBody JsonModel jsonModel) { //transforma o body da requisicao no objeto jsonModel
       return simulacaoService.respostaSimulacao(jsonModel);
   }
}
    

