package com.bancoeconomico.service;

import com.bancoeconomico.dto.*;
import com.bancoeconomico.model.*;
import com.bancoeconomico.model.enums.StatusClienteEnum;
import com.bancoeconomico.repository.ClienteRepository;
import com.bancoeconomico.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;

    public ClientePfDto createClientePf(ClientePfDto clientePfDto) {
        ClientePf clientePf = new ClientePf();
        clientePf.setId(clientePfDto.getCpf());
        clientePf.setCpf(clientePfDto.getCpf());
        clientePf.setNome(clientePfDto.getNome());
        clientePf.setDataCadastro(LocalDate.now());
        clientePf.setStatus(StatusClienteEnum.ATIVO);

        ContaCorrente contaCorrente = new ContaCorrente(clientePf);
        clientePf.setContas(Collections.singletonList(contaCorrente));

        clienteRepository.save(clientePf);
        contaRepository.save(contaCorrente);

        return clientePfDto;
    }

    public ClientePjDto createClientePj(ClientePjDto clientePJDto) {
        ClientePj clientePj = new ClientePj();
        clientePj.setId(clientePJDto.getCnpj());
        clientePj.setCnpj(clientePJDto.getCnpj());
        clientePj.setNome(clientePJDto.getNome());
        clientePj.setDataCadastro(LocalDate.now());
        clientePj.setStatus(StatusClienteEnum.ATIVO);

        ContaCorrente conta = new ContaCorrente(clientePj);
        clientePj.setContas(Collections.singletonList(contaCorrente));

        clienteRepository.save(clientePj);
        contaCorrenteRepository.save(contaCorrente);

        return clientePJDto;
    }

    // Additional methods for deposit, withdrawal, transfer, etc.
}

}
