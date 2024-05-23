package com.bancoeconomico.controller;

import com.bancoeconomico.dto.ClienteDto;
import com.bancoeconomico.dto.ClientePfDto;
import com.bancoeconomico.dto.ClientePjDto;
import com.bancoeconomico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable String id) {
        ClienteDto clienteDto = clienteService.getClienteById(id);
        if (clienteDto != null) {
            return ResponseEntity.ok(clienteDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pf")
    public ResponseEntity<ClientePfDto> createClientePF(@RequestBody ClientePfDto clientePfDto) {
        ClientePfDto createdClientePFDto = clienteService.createClientePF(clientePfDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClientePFDto);
    }

    @PostMapping("/pj")
    public ResponseEntity<ClientePjDto> createClientePJ(@RequestBody ClientePjDto clientePjDto) {
        ClientePjDto createdClientePJDto = clienteService.createClientePJ(clientePjDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClientePJDto);
    }
}
