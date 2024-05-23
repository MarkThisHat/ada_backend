package com.bancoeconomico.service.factory;

import com.bancoeconomico.model.*;

public class ContaFactory {

    public static Conta createConta(Cliente cliente, String tipoConta) {
        switch (tipoConta) {
            case "corrente":
                return new ContaCorrente(cliente);
            case "poupanca":
                if (cliente instanceof ClientePf) {
                    return new ContaPoupanca((ClientePf) cliente);
                } else {
                    throw new IllegalArgumentException("Apenas clientes PF podem ter conta poupança.");
                }
            case "investimento":
                return new ContaInvestimento(cliente);
            default:
                throw new IllegalArgumentException("Tipo de conta desconhecido.");
        }
    }
}
