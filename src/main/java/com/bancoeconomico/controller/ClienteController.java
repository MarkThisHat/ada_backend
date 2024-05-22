package com.bancoeconomico.controller;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable String id) {
        return clienteService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable String id) {
        clienteService.delete(id);
    }
}
