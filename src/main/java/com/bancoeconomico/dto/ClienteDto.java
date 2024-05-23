package com.bancoeconomico.dto;

import com.bancoeconomico.model.enums.StatusClienteEnum;

import java.time.LocalDate;
import java.util.List;

public interface ClienteDto {
    String getId();
    String getNome();
    LocalDate getDataCadastro();
    StatusClienteEnum getStatus();
    List<ContaDto> getContas();
}
