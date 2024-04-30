package com.bancoeconomico;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.ClientePF;
import com.bancoeconomico.model.ClientePJ;
import com.bancoeconomico.model.Conta;
import com.bancoeconomico.service.csv.AccountInitializationService;
import com.bancoeconomico.service.csv.CsvDataImporter;
import com.bancoeconomico.service.csv.CsvExporter;
import com.bancoeconomico.service.factory.OperacoesBancariasFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        CsvDataImporter importer = new CsvDataImporter();
        Optional<List<Cliente>> optionalClients = importer.importClients("pessoas.csv");

        optionalClients.ifPresent(clients -> {
            AccountInitializationService accountService = new AccountInitializationService();
            accountService.initializeAccounts(clients);

        CsvExporter exporter = new CsvExporter();
        exporter.exportClients(clients, "acao_marketing.csv");
        });

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

