package com.bancoeconomico.dto;

import com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaInvestimentoDto implements ContaDto {
    private Long id;
    private Long numero;
    private BigDecimal saldo;
    private LocalDate dataCriacao;
    private Cliente cliente;
    private BigDecimal investmentYield;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getNumero() {
        return numero;
    }

    @Override
    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getInvestmentYield() {
        return investmentYield;
    }

    public void setInvestmentYield(BigDecimal investmentYield) {
        this.investmentYield = investmentYield;
    }

}
