package com.bancoeconomico.model;

import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta {

    public ContaPoupanca() {
        /*
            JPA empty constructor
         */
    }

    public ContaPoupanca(ClientePF cliente) {
        super(cliente);
    }

}
