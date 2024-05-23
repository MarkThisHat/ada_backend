package com.bancoeconomico.service.impl;


import com.bancoeconomico.model.ClientePf;
import com.bancoeconomico.model.Conta;
import com.bancoeconomico.model.ContaInvestimento;
import com.bancoeconomico.service.OperacoesBancarias;

import java.math.BigDecimal;

public class OperacoesContaClientePF implements OperacoesBancarias<ClientePf> {

    private static final BigDecimal RENDIMENTO_INVESTIMENTO = BigDecimal.valueOf(1.01);

    @Override
    public void sacar(ClientePf cliente, Long numeroConta, BigDecimal valor) {
        Conta conta = OperacoesBancarias.super.getContaCliente(cliente, numeroConta);
        OperacoesBancarias.super.verificarSaldo(conta, valor);
        conta.setSaldo(conta.getSaldo().subtract(valor));
    }

    @Override
    public void investir(ClientePf cliente, BigDecimal valor) {
        Conta conta = OperacoesBancarias.super.getContaInvestimentoCliente(cliente);
        if (conta == null) {
            conta = new ContaInvestimento(cliente);
            cliente.getContas().add(conta);
        }
        valor = valor.multiply(RENDIMENTO_INVESTIMENTO);
        conta.setSaldo(conta.getSaldo().add(valor));
    }

}
