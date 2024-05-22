package com.bancoeconomico.service;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.ContaCorrente;
import com.bancoeconomico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        cliente.getContas().add(new ContaCorrente(cliente));
        return clienteRepository.save(cliente);
    }

    public Cliente findById(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void delete(String id) {
        clienteRepository.deleteById(id);
    }
}
