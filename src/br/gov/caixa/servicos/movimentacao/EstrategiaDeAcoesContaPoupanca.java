package br.gov.caixa.servicos.movimentacao;

import br.gov.caixa.enums.TipoAcao;

import java.util.EnumSet;

public class EstrategiaDeAcoesContaPoupanca implements EstrategiaDeMovimentacao {
    private static final EnumSet<TipoAcao> allowedActions = EnumSet.of(
            TipoAcao.SAQUE,
            TipoAcao.DEPOSITO,
            TipoAcao.TRANSFERENCIA,
            TipoAcao.CONSULTA_DE_SALDO
    );

    @Override
    public boolean permitirAcao(TipoAcao acao) {
        return allowedActions.contains(acao);
    }
}
