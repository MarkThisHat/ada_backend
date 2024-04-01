package br.gov.caixa.models;

import br.gov.caixa.enums.TipoAcao;

import java.time.LocalDate;
import java.util.EnumSet;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(String id, LocalDate data, Usuario titular) {
        super(id, data, titular);
        this.acoesPermitidas = EnumSet.of(
                TipoAcao.SAQUE,
                TipoAcao.DEPOSITO,
                TipoAcao.TRANSFERENCIA,
                TipoAcao.CONSULTA_DE_SALDO
        );
    }

    @Override
    public ContaInvestimento abreConta(String id, LocalDate data, Usuario usuario) {
        return new ContaInvestimento(id, data, usuario);
    }

    public double creditaRendimento(double saldo, int months) {
        double indice;

        if (titular.getTipo().equals("PF")) {
            indice = 0.01;
        } else {
            indice = 0.2;
        }

        for (int i = 0; i < months; i++) {
            saldo += saldo * indice;
        }
        return saldo;
    }
}
