package br.gov.caixa.models;

import br.gov.caixa.enums.TipoAcao;

import java.time.LocalDate;
import java.util.EnumSet;

public class ContaCorrente extends Conta {
    private ContaInvestimento contaInvestimento = null;

    public ContaCorrente(String id, LocalDate data, Usuario titular) {
        super(id, data, titular);
        this.acoesPermitidas = EnumSet.of(
                TipoAcao.SAQUE,
                TipoAcao.DEPOSITO,
                TipoAcao.TRANSFERENCIA,
                TipoAcao.INVESTIMENTO,
                TipoAcao.CONSULTA_DE_SALDO
        );
    }

    @Override
    public ContaCorrente abreConta(String id, LocalDate data, Usuario usuario) {
        return new ContaCorrente(id, data, usuario);
    }

    public boolean realizaInvestimento(double valor) {
        if (contaInvestimento == null) {
            contaInvestimento = contaInvestimento.abreConta(this.id, LocalDate.now(), this.titular );
            titular.adicionaConta(contaInvestimento);
        }
        if (valor > saldo) {
            return false;
        }
        saldo -= valor;
        contaInvestimento.recebeTransferencia(valor);
        return true;
    }
}
