package com.bancoeconomico.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class ContaPoupanca extends Conta {
    private BigDecimal interestRate;

    public ContaPoupanca() {
        /*
            JPA empty constructor
         */
    }

    public ContaPoupanca(ClientePf cliente) {
        super(cliente);
        this.interestRate = BigDecimal.ZERO;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void applyInvestment(BigDecimal amount) {
        BigDecimal yield = amount;
        if (getCliente() instanceof ClientePf) {
            yield = yield.multiply(BigDecimal.valueOf(0.01));
        }
        setSaldo(getSaldo().add(amount).add(yield));
    }
}
