package br.gov.caixa.servicos.tarifas;

import java.math.BigDecimal;

public interface EstrategiaDeCalculoDeTarifas {

    BigDecimal calcularTarifa(BigDecimal valor);
}
