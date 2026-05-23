package com.api_emprestimo.simulador.service;
import com.api_emprestimo.simulador.dto.Parcela;
import com.api_emprestimo.simulador.dto.ResultadoSimulacao;
import com.api_emprestimo.simulador.dto.SimulacaoResponse;
import com.api_emprestimo.simulador.model.JsonModel;

import org.apache.naming.factory.ResourceLinkFactory;
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

    
        ResultadoSimulacao resultadoSimulacaoSAC = CalculaSac(valorDesejado, prazo, simulacaoResponse.getTaxaJuros());
        ResultadoSimulacao resultadoSimulacaoPRICE = CalculaPrice(valorDesejado, prazo, simulacaoResponse.getTaxaJuros());


        List<ResultadoSimulacao> resultadoSimulacao = new ArrayList<>();
        resultadoSimulacao.add(resultadoSimulacaoSAC);
        resultadoSimulacao.add(resultadoSimulacaoPRICE);
        simulacaoResponse.setResultadoSimulacao(resultadoSimulacao);
        return simulacaoResponse;

    }

    public String RetornaPong(){
        return "pong";
    }


    public ResultadoSimulacao CalculaSac(double valorDesejado, int prazo, double taxaJuros){

        ResultadoSimulacao resultadoSimulacaoSAC = new ResultadoSimulacao();
        resultadoSimulacaoSAC.setTipo("SAC");
        List<Parcela> listParcelasSAC = new ArrayList<>();

        double saldoDevedor = valorDesejado;

        double amortizacao = valorDesejado/prazo;

        for (int i=1;i<=prazo;i++){

            double juros = saldoDevedor*taxaJuros;
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
        return resultadoSimulacaoSAC;
    }

            public ResultadoSimulacao CalculaPrice(double valorDesejado, int prazo, double taxaJuros){

                ResultadoSimulacao resultadoSimulacaoPRICE = new ResultadoSimulacao();
                resultadoSimulacaoPRICE.setTipo("PRICE");
                List<Parcela> listParcelasPRICE = new ArrayList<>();
            
                double saldoDevedor = valorDesejado;
            

                double prestacao = valorDesejado *
                        (taxaJuros * Math.pow(1 + taxaJuros, prazo)) / (Math.pow(1 + taxaJuros, prazo) - 1);

                for (int i=1;i<=prazo;i++){

                    double juros = saldoDevedor * taxaJuros;
                    double amortizacao = prestacao - juros;
                    Parcela parcelaPRICE = new Parcela();
                    parcelaPRICE.setNumero(i);
                    parcelaPRICE.setValorAmortizacao(amortizacao);
                    parcelaPRICE.setValorJuros(juros);
                    parcelaPRICE.setValorPrestacao(prestacao);
                    listParcelasPRICE.add(parcelaPRICE);
                    saldoDevedor -= amortizacao;
         
        
                }
                resultadoSimulacaoPRICE.setParcelas(listParcelasPRICE);
                return resultadoSimulacaoPRICE;
            }
}   