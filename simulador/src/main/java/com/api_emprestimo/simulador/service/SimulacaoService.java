package com.api_emprestimo.simulador.service;
import com.api_emprestimo.simulador.dto.Parcela;
import com.api_emprestimo.simulador.dto.ResultadoSimulacao;
import com.api_emprestimo.simulador.dto.SimulacaoResponse;
import com.api_emprestimo.simulador.model.JsonModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class SimulacaoService {
    public SimulacaoResponse respostaSimulacao(JsonModel jsonModel){
        double valorDesejado = jsonModel.getValorDesejado();
        int prazo = jsonModel.getPrazo();

        SimulacaoResponse simulacaoResponse = new SimulacaoResponse();
        simulacaoResponse.setIdSimulacao(1);
        simulacaoResponse.setCodigoProduto(2);
        simulacaoResponse.setTaxaJuros(0.05);
        simulacaoResponse.setDescricaoProduto("Emprestimo");

        // resultadoSimulação -> Tipo (SAC ou PRICE) + lista de Parcelas
        ResultadoSimulacao resultadoSimulacaoSAC = new ResultadoSimulacao();
        resultadoSimulacaoSAC.setTipo("SAC");

        List<ResultadoSimulacao> resultadoSimulacoesList = new ArrayList<ResultadoSimulacao>();
        resultadoSimulacoesList.add(resultadoSimulacaoSAC);

        List<Parcela> listParcelasSAC = new ArrayList<>();

        //SAC
        double saldoDevedor = valorDesejado;
        double taxa = 0.05;
        double amortizacao = valorDesejado/prazo;

        for (int i=1;i<=prazo;i++){

            double juros = saldoDevedor*taxa;
            double prestacao = amortizacao + juros;

            Parcela parcelaSAC = new Parcela();
            parcelaSAC.setNumero(i);
            parcelaSAC.setValorAmortizacao(amortizacao);
            parcelaSAC.setValorJuros(juros);
            parcelaSAC.setValorPrestacao(prestacao);

            listParcelasSAC.add(parcelaSAC);
            
            saldoDevedor -= amortizacao;
        }
        resultadoSimulacaoSAC.setParcelas(listParcelasSAC);


       ResultadoSimulacao resultadoSimulacaoPRICE = new ResultadoSimulacao();
       resultadoSimulacaoPRICE.setTipo("PRICE");

       resultadoSimulacoesList.add(resultadoSimulacaoPRICE);


        List<Parcela> listParcelasPRICE = new ArrayList<>();
        //PRICE
        saldoDevedor = valorDesejado;

        double prestacao = valorDesejado *
                (taxa * Math.pow(1 + taxa, prazo)) / (Math.pow(1 + taxa, prazo) - 1);

        for (int i=1;i<=prazo;i++){

            double juros = saldoDevedor * taxa;
            amortizacao = prestacao - juros;


            Parcela parcelaPRICE = new Parcela();
            parcelaPRICE.setNumero(i);
            parcelaPRICE.setValorAmortizacao(amortizacao);
            parcelaPRICE.setValorJuros(juros);
            parcelaPRICE.setValorPrestacao(prestacao);

            listParcelasPRICE.add(parcelaPRICE);

            saldoDevedor -= amortizacao;

        }

        resultadoSimulacaoSAC.setParcelas(listParcelasSAC);
        resultadoSimulacaoPRICE.setParcelas(listParcelasPRICE);
        simulacaoResponse.setResultadoSimulacao(resultadoSimulacoesList);
        return simulacaoResponse;
    }

    public String RetornaPong(){
        return "pong";
    }
}