package com.bancoeconomico.model;

import jakarta.persistence.Entity;

@Entity
public class ClientePf extends Cliente {

    private String cpf;

    public ClientePf() {
        /*
            JPA empty constructor
         */
    }

    public ClientePf(String nome, String cpf) {
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setId(String cpf) {
        this.cpf = cpf;
    }
}
