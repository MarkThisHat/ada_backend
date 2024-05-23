package com.bancoeconomico.model;

import com.bancoeconomico.model.enums.StatusClienteEnum;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente {

    @Id
    private String id;

    private String nome;
    private LocalDate dataCadastro;
    @Enumerated(EnumType.STRING)
    private StatusClienteEnum status;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Conta> contas;

    public Cliente () {
        /*
            JPA empty constructor
         */
    }

    public Cliente(String nome) {
        this.nome = nome;
        this.dataCadastro = LocalDate.now();
        this.status = StatusClienteEnum.ATIVO;
        this.contas = new ArrayList<>();
        this.contas.add(new ContaCorrente(this));
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public StatusClienteEnum getStatus() {
        return status;
    }

    public void setStatus(StatusClienteEnum status) {
        this.status = status;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
