package com.bancoeconomico.model;

import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {

    public ContaCorrente() {
        /*
            JPA empty constructor
         */
    }

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

}
