package com.bancoeconomico.dto;

import com.bancoeconomico.model.Conta;
import com.bancoeconomico.model.enums.StatusClienteEnum;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

public class ClientePfDto implements ClienteDto {
    @Id
    private String cpf; // Using cpf as ID
    private String nome;
    private LocalDate dataCadastro;
    private StatusClienteEnum status;
    private List<ContaDto> contas;

    public ClientePfDto(String cpf,
                        String nome,
                        LocalDate dataCadastro,
                        StatusClienteEnum status,
                        List<Conta> contas) {}

    public ClientePfDto() {}

    @Override
    public String getId() {
        return cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public StatusClienteEnum getStatus() {
        return status;
    }

    @Override
    public List<ContaDto> getContas() {
        return contas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setStatus(StatusClienteEnum status) {
        this.status = status;
    }

    public void setContas(List<ContaDto> contas) {
        this.contas = contas;
    }
}
