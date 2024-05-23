package com.bancoeconomico.dto;

import com.bancoeconomico.model.enums.StatusClienteEnum;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
public interface ClienteDto {
    String getId();
    String getNome();
    LocalDate getDataCadastro();
    StatusClienteEnum getStatus();
    List<ContaDto> getContas();
}
