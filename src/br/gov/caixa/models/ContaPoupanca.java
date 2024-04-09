package br.gov.caixa.models;

import br.gov.caixa.enums.TipoAcao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumSet;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String id, LocalDate dataAtualizacao, Usuario titular) {
        super(id, dataAtualizacao, titular);

    }

    @Override
    public boolean saque(BigDecimal valor) {
        if (this.getSaldo().compareTo(valor) >= 0) {
            this.setSaldo(this.getSaldo().subtract(valor));
            this.setDataAtualizacao(LocalDate.now());
            return true;
        }
        return false;
    }

    private void setDataAtualizacao(LocalDate now) {
    }

    private void setSaldo(BigDecimal subtract) {
    }

    public void deposito(BigDecimal valor) {
        super.deposito(valor);
    }
}
