package com.api_emprestimo.simulador.dto;


import com.api_emprestimo.simulador.model.JsonModel;

import java.util.List;

public class SimulacaoResponse {
    private int idSimulacao;
    private int codigoProduto;
    private String descricaoProduto;
    private double taxaJuros;
    private List<ResultadoSimulacao> resultadoSimulacao;


    public int getIdSimulacao() {
        return idSimulacao;
    }
    public void setIdSimulacao(int idSimulacao) {
        this.idSimulacao = idSimulacao;
    }
    public int getCodigoProduto() {
        return codigoProduto;
    }
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
    public double getTaxaJuros() {
        return taxaJuros;
    }
    public void setTaxaJuros(double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }
    public List<ResultadoSimulacao> getResultadoSimulacao() {
        return resultadoSimulacao;
    }
    public void setResultadoSimulacao(List<ResultadoSimulacao> resultadoSimulacaoList) {
        this.resultadoSimulacao = resultadoSimulacaoList;
    }
}
