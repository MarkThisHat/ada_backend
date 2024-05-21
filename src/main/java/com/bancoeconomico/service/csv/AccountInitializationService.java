package com.bancoeconomico.service.csv;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.ContaCorrente;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AccountInitializationService {

    public void initializeAccounts(List<Cliente> clients) {
        clients.forEach(client -> {
            if (client.getContas().isEmpty()) {
                ContaCorrente cc = new ContaCorrente(client);
                client.getContas().add(cc);
            }
            client.getContas().get(0).setSaldo(new BigDecimal("50"));
        });
    }
}
