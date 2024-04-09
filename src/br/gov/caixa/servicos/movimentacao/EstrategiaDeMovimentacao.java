package br.gov.caixa.servicos.movimentacao;

import br.gov.caixa.enums.TipoAcao;

import java.util.EnumSet;

public interface EstrategiaDeMovimentacao {
    boolean permitirAcao(TipoAcao acao);
}

