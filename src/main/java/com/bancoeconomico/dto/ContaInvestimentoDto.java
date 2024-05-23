package com.bancoeconomico.dto;

import com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaInvestimentoDto implements ContaDto {
    private Long id;
    private Integer numero;
    private BigDecimal saldo;
    private LocalDate dataCriacao;
    private Cliente cliente;
    private BigDecimal investmentYield;

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

    public BigDecimal getInvestmentYield() {
        return investmentYield;
    }

    public void setInvestmentYield(BigDecimal investmentYield) {
        this.investmentYield = investmentYield;
    }

}
