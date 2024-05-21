package main.java.com.bancoeconomico.service;

import main.java.com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;

public interface Investimento<T extends Cliente> {

    void investir(T cliente, BigDecimal valor);

}
