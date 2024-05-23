package com.bancoeconomico.dto;

import com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaDTO {
    private Long id;
    private Integer numero;
    private BigDecimal saldo;
    private LocalDate dataCriacao;
    private Cliente cliente;
}
