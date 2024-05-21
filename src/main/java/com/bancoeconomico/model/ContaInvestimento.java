package com.bancoeconomico.model;

import jakarta.persistence.Entity;

@Entity
public class ContaInvestimento extends Conta {

    public ContaInvestimento() {
        /*
            JPA empty constructor
         */
    }

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
    }

}
