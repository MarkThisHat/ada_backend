package com.bancoeconomico.model;

import jakarta.persistence.Entity;

@Entity
public class ClientePj extends Cliente {

    private String cnpj;

    public ClientePj() {
        /*
            JPA empty constructor
         */
    }

    public ClientePj(String nome, String cnpj) {
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

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setId(String cnpj) {
        this.cnpj = cnpj;
    }
}
