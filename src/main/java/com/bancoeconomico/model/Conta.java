package com.bancoeconomico.model;

import com.bancoeconomico.service.csv.AccountRegistryControl;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numero;
    private BigDecimal saldo;
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Conta() {
        /*
            JPA empty constructor
         */
    }

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.numero = AccountRegistryControl.generateUniqueNumber();
        this.dataCriacao = LocalDate.now();
        this.saldo = BigDecimal.ZERO;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
