package com.bancoeconomico.model;

import com.bancoeconomico.service.csv.AccountRegistryControl;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
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

    public Integer getNumero() {
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

    //protected abstract String generateAccountNumber();
}
