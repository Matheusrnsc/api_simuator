package com.api_emprestimo.simulador.dto;

import java.util.List;

public class ResultadoSimulacao {

    private String tipo;
    private List<Parcela> parcelas;


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }
}
