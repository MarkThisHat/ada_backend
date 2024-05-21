package main.java.com.bancoeconomico.service;

import main.java.com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;

public interface Deposito<T extends Cliente> {

    void depositar(T cliente, Integer numeroConta, BigDecimal valor);

}
