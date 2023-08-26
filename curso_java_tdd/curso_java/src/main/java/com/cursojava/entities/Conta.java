package com.cursojava.entities;

import com.cursojava.exceptions.SaldoInsuficienteException;

public class Conta {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        validarSeValorInformadoEMenorOuIgualAZero(valor);

        saldo += valor;
    }

    public void sacar(double valor) {
        validarSeSaldoInsuficiente(valor);
        validarSeValorInformadoEMenorOuIgualAZero(valor);

        saldo -= valor; 
    }

    private void validarSeSaldoInsuficiente(double valor) {
        if (valor > saldo) 
            throw new SaldoInsuficienteException("Saldo insuficiente");
    }

    private void validarSeValorInformadoEMenorOuIgualAZero(double valor) {
        if (valor <= 0)
            throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}
