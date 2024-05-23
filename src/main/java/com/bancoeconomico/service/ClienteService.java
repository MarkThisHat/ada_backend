package com.bancoeconomico.service;

import com.bancoeconomico.dto.*;
import com.bancoeconomico.model.*;
import com.bancoeconomico.model.enums.StatusClienteEnum;
import com.bancoeconomico.repository.ClienteRepository;
import com.bancoeconomico.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
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

    public ClientePjDto createClientePj(ClientePjDto clientePjDto) {
        ClientePj clientePj = new ClientePj();
        clientePj.setId(clientePjDto.getCnpj());
        clientePj.setCnpj(clientePjDto.getCnpj());
        clientePj.setNome(clientePjDto.getNome());
        clientePj.setDataCadastro(LocalDate.now());
        clientePj.setStatus(StatusClienteEnum.ATIVO);

        ContaCorrente contaCorrente = new ContaCorrente(clientePj);
        clientePj.setContas(Collections.singletonList(contaCorrente));

        clienteRepository.save(clientePj);
        contaRepository.save(contaCorrente);

        return clientePjDto;
    }

    public ClienteDto getClienteById(String id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            return mapToDto(cliente);
        }
        throw new EntityNotFoundException("Cliente not found with id: " + id);
    }

    private ClienteDto mapToDto(Cliente cliente) {
        if (cliente instanceof ClientePf) {
            ClientePf clientePF = (ClientePf) cliente;
            ClientePfDto clientePFDto = new ClientePfDto();
            clientePFDto.setCpf(clientePF.getCpf());
            clientePFDto.setNome(clientePF.getNome());
            clientePFDto.setDataCadastro(clientePF.getDataCadastro());
            clientePFDto.setStatus(clientePF.getStatus());
            clientePFDto.setContas(clientePF.getContas().stream().map(this::mapToContaDto).collect(Collectors.toList()));
            return clientePFDto;
        } else if (cliente instanceof ClientePj) {
            ClientePj clientePJ = (ClientePj) cliente;
            ClientePjDto clientePJDto = new ClientePjDto();
            clientePJDto.setCnpj(clientePJ.getCnpj());
            clientePJDto.setNome(clientePJ.getNome());
            clientePJDto.setDataCadastro(clientePJ.getDataCadastro());
            clientePJDto.setStatus(clientePJ.getStatus());
            clientePJDto.setContas(clientePJ.getContas().stream().map(this::mapToContaDto).collect(Collectors.toList()));
            return clientePJDto;
        }
        throw new IllegalArgumentException("Unknown Cliente type");
    }

    private ContaDto mapToContaDto(Conta conta) {
        if (conta instanceof ContaCorrente) {
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            ContaCorrenteDto dto = new ContaCorrenteDto();
            dto.setNumero(contaCorrente.getNumero());
            dto.setSaldo(contaCorrente.getSaldo());
            dto.setDataCriacao(contaCorrente.getDataCriacao());
            dto.setCliente(contaCorrente.getCliente());
            dto.setOverdraftLimit(contaCorrente.getOverdraftLimit());
            return dto;
        } else if (conta instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
            ContaPoupancaDto dto = new ContaPoupancaDto();
            dto.setNumero(contaPoupanca.getNumero());
            dto.setSaldo(contaPoupanca.getSaldo());
            dto.setDataCriacao(contaPoupanca.getDataCriacao());
            dto.setCliente(contaPoupanca.getCliente());
            dto.setInterestRate(contaPoupanca.getInterestRate());
            return dto;
        } else if (conta instanceof ContaInvestimento) {
            ContaInvestimento contaInvestimento = (ContaInvestimento) conta;
            ContaInvestimentoDto dto = new ContaInvestimentoDto();
            dto.setNumero(contaInvestimento.getNumero());
            dto.setSaldo(contaInvestimento.getSaldo());
            dto.setDataCriacao(contaInvestimento.getDataCriacao());
            dto.setCliente(contaInvestimento.getCliente());
            dto.setInvestmentYield(contaInvestimento.getInvestmentYield());
            return dto;
        }
        throw new IllegalArgumentException("Tipo desconhecido de conta");
    }

    public void makeInvestment(String clienteId, BigDecimal amount, String accountType) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente " + clienteId + " não localizado."));

        Conta conta = contaRepository.findByCliente(cliente)
                .orElseThrow(() -> new IllegalArgumentException("Conta não localizada para o cliente"));

        if ("investimento".equals(accountType) && conta instanceof ContaInvestimento) {
            ((ContaInvestimento) conta).applyInvestment(amount);
        } else if ("poupanca".equals(accountType) && conta instanceof ContaPoupanca) {
            ((ContaPoupanca) conta).applyInvestment(amount);
        } else {
            throw new IllegalArgumentException("Rendimento só ocorre em contas poupança ou investimento");
        }
        contaRepository.save(conta);
    }
}
