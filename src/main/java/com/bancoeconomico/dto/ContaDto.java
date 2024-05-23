package com.bancoeconomico.dto;

import com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ContaDto {
    Long getId();
    Integer getNumero();
    BigDecimal getSaldo();
    LocalDate getDataCriacao();
    Cliente getCliente();
}
