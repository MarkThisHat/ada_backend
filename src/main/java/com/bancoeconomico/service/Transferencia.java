package main.java.com.bancoeconomico.service;

import main.java.com.bancoeconomico.model.Cliente;
import main.java.com.bancoeconomico.model.Conta;

import java.math.BigDecimal;

public interface Transferencia<T extends Cliente> {

    void transferir(T cliente, Integer numeroContaOrigem, Conta destino, BigDecimal valor);

}
