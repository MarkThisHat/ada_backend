package br.gov.caixa.models;

import br.gov.caixa.enums.Status;
import br.gov.caixa.servicos.tarifas.EstrategiaDeCalculoDeTarifas;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Conta {
    private String id;
    private BigDecimal saldo;
    private LocalDate dataAtualizacao;
    private Status status;
    private Usuario titular;

    public Conta(String id, LocalDate dataAtualizacao, Usuario titular) {
        this.id = id;
        this.saldo = BigDecimal.ZERO;
        this.dataAtualizacao = dataAtualizacao;
        this.status = Status.ATIVO;
        this.titular = titular;
    }

    public void deposito(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
        this.dataAtualizacao = LocalDate.now();
    }

    public abstract boolean saque(BigDecimal valor);

    public String getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Status getStatus() {
        return status;
    }

    public Usuario getTitular() {
        return titular;
    }

    public void setEstrategiaDeCalculoDeTarifas(EstrategiaDeCalculoDeTarifas estrategia) {
    }

    public EstrategiaDeCalculoDeTarifas getEstrategiaDeCalculoDeTaxas() {
        return null;
    }
}