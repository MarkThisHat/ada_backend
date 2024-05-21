package com.bancoeconomico.model;

import jakarta.persistence.Entity;

@Entity
public class ClientePF extends Cliente {

    private String cpf;

    public ClientePF () {
        /*
            JPA empty constructor
         */
    }

    public ClientePF(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getId() {
        return getCpf();
    }
}
