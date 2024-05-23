package com.bancoeconomico.dto;

import com.bancoeconomico.model.Cliente;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContaCorrenteDto.class, name = "corrente"),
        @JsonSubTypes.Type(value = ContaPoupancaDto.class, name = "poupanca"),
        @JsonSubTypes.Type(value = ContaInvestimentoDto.class, name = "investimento")
})
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
