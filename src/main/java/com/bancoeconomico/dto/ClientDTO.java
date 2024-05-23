package com.bancoeconomico.dto;

import com.bancoeconomico.model.enums.StatusClienteEnum;

import java.time.LocalDate;
import java.util.List;

public class ClientDTO {
    private String id;
    private String nome;
    private LocalDate dataCadastro;
    private StatusClienteEnum status;
    private List<ContaDTO> contas;

}
