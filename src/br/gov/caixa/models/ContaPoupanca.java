package br.gov.caixa.models;

import br.gov.caixa.enums.TipoAcao;

import java.time.LocalDate;
import java.util.EnumSet;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String id, LocalDate data, Usuario titular) {
        super(id, data, titular);
        this.acoesPermitidas = EnumSet.of(
                TipoAcao.SAQUE,
                TipoAcao.DEPOSITO,
                TipoAcao.TRANSFERENCIA,
                TipoAcao.CONSULTA_DE_SALDO
        );
    }

    @Override
    public ContaPoupanca abreConta(String id, LocalDate data, Usuario usuario) {
        return new ContaPoupanca(id, data, usuario);
    }
}
