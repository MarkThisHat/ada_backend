package br.gov.caixa.models;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ContaCorrente extends Conta {
    private ContaInvestimento contaInvestimento;

    public ContaCorrente(String id, LocalDate dataAtualizacao, Usuario titular) {
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

    public void abrirContaInvestimento() {
        if (this.contaInvestimento == null) {

        }
    }
}