package com.bancoeconomico.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class ContaInvestimento extends Conta {
    private BigDecimal investmentYield;

    public ContaInvestimento() {
        /*
            JPA empty constructor
         */
    }

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
        this.investmentYield = BigDecimal.ZERO;
    }

    public BigDecimal getInvestmentYield() {
        return investmentYield;
    }

    public void setInvestmentYield(BigDecimal investmentYield) {
        this.investmentYield = investmentYield;
    }

    public void applyInvestment(BigDecimal amount) {
        BigDecimal yield = amount;
        if (getCliente() instanceof ClientePf) {
            yield = yield.multiply(BigDecimal.valueOf(0.01));
        } else if (getCliente() instanceof ClientePj) {
            yield = yield.multiply(BigDecimal.valueOf(0.02));
        }
        setSaldo(getSaldo().add(amount).add(yield));
    }
}
