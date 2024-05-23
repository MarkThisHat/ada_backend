package com.bancoeconomico.service;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.Conta;

import java.math.BigDecimal;

public interface Transferencia<T extends Cliente> {

    void transferir(T cliente, Long numeroContaOrigem, Conta destino, BigDecimal valor);

}
