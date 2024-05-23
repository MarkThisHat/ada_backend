package com.bancoeconomico.dto;

import com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ContaDto {
    Long getId();
    Long getNumero();
    BigDecimal getSaldo();
    LocalDate getDataCriacao();
    Cliente getCliente();
    void setNumero(Long numero);
    void setSaldo(BigDecimal saldo);
    void setDataCriacao(LocalDate dataCriacao);
    void setCliente(Cliente cliente);
}
