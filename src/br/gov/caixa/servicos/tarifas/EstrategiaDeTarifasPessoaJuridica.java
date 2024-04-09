package br.gov.caixa.servicos.tarifas;

import java.math.BigDecimal;
import br.gov.caixa.constantes.TarifasConstantes;

public class EstrategiaDeTarifasPessoaJuridica implements EstrategiaDeCalculoDeTarifas {

    @Override
    public BigDecimal calcularTarifa(BigDecimal valor) {
        return valor.multiply(TarifasConstantes.TARIFA_MOV_PJ);
    }
}
