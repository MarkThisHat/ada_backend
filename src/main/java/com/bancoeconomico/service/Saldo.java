package main.java.com.bancoeconomico.service;

import main.java.com.bancoeconomico.model.Cliente;

import java.math.BigDecimal;

public interface Saldo<T extends Cliente> {

    BigDecimal consultarSaldo(T cliente, Integer numeroConta);

}
