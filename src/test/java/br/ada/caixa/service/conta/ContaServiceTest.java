package br.ada.caixa.service.conta;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.TipoConta;
import br.ada.caixa.exceptions.ValidacaoException;


import br.ada.caixa.respository.ClienteRepository;
import br.ada.caixa.respository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAbrirContaPoupanca() {
        String cpf = "12345678900";
        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setDocumento(cpf);

        when(clienteRepository.findByDocumento(cpf)).thenReturn(Optional.of(cliente));

        Conta contaPoupanca = new Conta();
        contaPoupanca.setId(UUID.randomUUID());
        contaPoupanca.setTipo(TipoConta.CONTA_POUPANCA);
        contaPoupanca.setCliente(cliente);
        contaPoupanca.setSaldo(BigDecimal.ZERO);

        when(contaRepository.save(any(Conta.class))).thenReturn(contaPoupanca);

        Conta result = contaService.abrirContaPoupanca(cpf);

        assertNotNull(result);
        assertEquals(TipoConta.CONTA_POUPANCA, result.getTipo());
        assertEquals(cliente, result.getCliente());
        assertEquals(BigDecimal.ZERO, result.getSaldo());
        verify(contaRepository, times(1)).save(any(Conta.class));
    }

    @Test
    public void testAbrirContaPoupancaClienteNaoEncontrado() {
        String cpf = "12345678900";

        when(clienteRepository.findByDocumento(cpf)).thenReturn(Optional.empty());

        assertThrows(ValidacaoException.class, () -> contaService.abrirContaPoupanca(cpf));
    }
}
