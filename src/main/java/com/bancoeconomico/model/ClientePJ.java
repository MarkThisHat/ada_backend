package com.bancoeconomico.model;

import jakarta.persistence.Entity;

@Entity
public class ClientePJ extends Cliente {

    private String cnpj;

    public ClientePJ () {
        /*
            JPA empty constructor
         */
    }

    public ClientePJ(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getId() {
        return getCnpj();
    }
}
