package com.bancoeconomico.dto;

import com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPoupancaDto implements ContaDto {
    private Long id;
    private Integer numero;
    private BigDecimal saldo;
    private LocalDate dataCriacao;
    private Cliente cliente;
    private BigDecimal interestRate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Integer getNumero() {
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

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
