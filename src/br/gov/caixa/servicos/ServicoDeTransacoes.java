package br.gov.caixa.servicos;

import br.gov.caixa.models.Conta;

import java.math.BigDecimal;

public class ServicoDeTransacoes {

    public boolean transferencia(Conta origem, Conta destino, BigDecimal valor) {
        BigDecimal valorFinal = valor.multiply(origem.getEstrategiaDeCalculoDeTaxas().calcularTarifa(valor));

        if (origem.getSaldo().compareTo(valorFinal) < 0) {
            return false;
        }

        origem.saque(valorFinal);
        destino.deposito(valor);
        return true;
    }
}
