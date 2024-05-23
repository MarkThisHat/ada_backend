package com.bancoeconomico.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class ContaCorrente extends Conta {

    private BigDecimal overdraftLimit;

    public ContaCorrente() {
        /*
            JPA empty constructor
         */
    }

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }


    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
