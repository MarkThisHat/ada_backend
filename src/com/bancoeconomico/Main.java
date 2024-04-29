package com.bancoeconomico;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.ClientePF;
import com.bancoeconomico.model.ClientePJ;
import com.bancoeconomico.model.Conta;
import com.bancoeconomico.service.factory.OperacoesBancariasFactory;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Cliente clientePF = new ClientePF("Cliente PF", "232323232");
        deposito(clientePF, BigDecimal.valueOf(100));
        saque(clientePF, BigDecimal.valueOf(90));
        deposito(clientePF, BigDecimal.valueOf(100));
        investimento(clientePF, BigDecimal.valueOf(100));

        Cliente clientePJ = new ClientePJ("Cliente PJ", "232323232");
        deposito(clientePJ, BigDecimal.valueOf(100));
        saque(clientePJ, BigDecimal.valueOf(90));
        deposito(clientePJ, BigDecimal.valueOf(100));
        investimento(clientePJ, BigDecimal.valueOf(100));

        transferir(clientePF, clientePJ, BigDecimal.valueOf(50));

        imprimirTodosSaldos(clientePF);
        imprimirTodosSaldos(clientePJ);

    }

    static void deposito(Cliente cliente, BigDecimal valor) {
        Conta conta = cliente.getContas().get(0);
        OperacoesBancariasFactory.getInstance().get(cliente)
                .depositar(cliente, conta.getNumero(), valor);
        print("deposito: " + valor + " saldo " + conta.getSaldo());
    }

    static void saque(Cliente cliente, BigDecimal valor) {
        Conta conta = cliente.getContas().get(0);
        OperacoesBancariasFactory.getInstance().get(cliente)
                .sacar(cliente, conta.getNumero(), valor);
        print("saque: " + valor + " saldo " + conta.getSaldo());
    }

    static void transferir(Cliente clienteOrigem, Cliente clienteDestino, BigDecimal valor) {
        Conta contaOrigem = clienteOrigem.getContas().get(0);
        Conta contaDestino = clienteDestino.getContas().get(0);
        OperacoesBancariasFactory.getInstance().get(clienteOrigem)
                .transferir(clienteOrigem, contaOrigem.getNumero(), contaDestino, valor);
        print("transferencia origem: " + valor + " saldo " + contaOrigem.getSaldo());
        print("transferencia destino: " + valor + " saldo " + contaDestino.getSaldo());
    }

    static void investimento(Cliente cliente, BigDecimal valor) {
        Conta conta = cliente.getContas().get(0);
        OperacoesBancariasFactory.getInstance().get(cliente)
                .investir(cliente, valor);
        print("investimento: " + valor + " saldo " + conta.getSaldo());
    }

    static void imprimirTodosSaldos(Cliente cliente) {
        print("SALDOS ===============");
        print("Cliente: " + cliente.getNome());
        BigDecimal saldoTotal = BigDecimal.ZERO;
        for (Conta conta : cliente.getContas()) {
            print(conta.getClass().getSimpleName() + " - " + conta.getSaldo());
            saldoTotal = saldoTotal.add(conta.getSaldo());
        }
        print("Total: " + saldoTotal);
        print("SALDOS ===============");
    }

    static void print(Object o) {
        System.out.println(o);
    }

}