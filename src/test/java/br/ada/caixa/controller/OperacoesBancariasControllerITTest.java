package br.ada.caixa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.InvestimentoRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransferenciaRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.service.conta.ContaService;
import br.ada.caixa.service.operacoesbancarias.deposito.DepositoService;
import br.ada.caixa.service.operacoesbancarias.investimento.InvestimentoService;
import br.ada.caixa.service.operacoesbancarias.saldo.SaldoService;
import br.ada.caixa.service.operacoesbancarias.saque.SaqueService;
import br.ada.caixa.service.operacoesbancarias.transferencia.TransferenciaService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@SpringBootTest
public class OperacoesBancariasControllerITTest {

    @Mock
    private DepositoService depositoService;

    @Mock
    private SaqueService saqueService;

    @Mock
    private TransferenciaService transferenciaService;

    @Mock
    private SaldoService saldoService;

    @Mock
    private InvestimentoService investimentoService;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private OperacoesBancariasController operacoesBancariasController;

    void OperacoesBancariasControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDepositar() {
        DepositoRequestDto depositoRequestDto = new DepositoRequestDto();
        depositoRequestDto.setNumeroConta(123L);
        depositoRequestDto.setValor(new BigDecimal("100.00"));

        ResponseEntity<Void> response = operacoesBancariasController.depositar(depositoRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testSacar() {
        SaqueRequestDto saqueRequestDto = new SaqueRequestDto();
        saqueRequestDto.setNumeroConta(123L);
        saqueRequestDto.setValor(new BigDecimal("100.00"));

        ResponseEntity<Void> response = operacoesBancariasController.sacar(saqueRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testTransferir() {
        TransferenciaRequestDto transferenciaRequestDto = new TransferenciaRequestDto();
        transferenciaRequestDto.setNumeroContaOrigem(123L);
        transferenciaRequestDto.setNumeroContaDestino(456L);
        transferenciaRequestDto.setValor(new BigDecimal("100.00"));

        // No return value to assert, just invoke the method
        operacoesBancariasController.transferencia(transferenciaRequestDto);
    }

    @Test
    void testConsultarSaldo() {
        Long numeroConta = 123L;
        BigDecimal saldo = new BigDecimal("1000.00");

        when(saldoService.consultarSaldo(numeroConta)).thenReturn(saldo);

        ResponseEntity<SaldoResponseDto> response = operacoesBancariasController.consultarSaldo(numeroConta);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(numeroConta, response.getBody().getNumeroConta());
        assertEquals(saldo, response.getBody().getSaldo());
    }

    @Test
    void testInvestir() {
        InvestimentoRequestDto investimentoRequestDto = new InvestimentoRequestDto();
        investimentoRequestDto.setDocumentoCliente("12345678900");
        investimentoRequestDto.setValor(new BigDecimal("100.00"));

        // Mock return value
        var contaInvestimento = new Conta();
        contaInvestimento.setNumero(123L);
        contaInvestimento.setSaldo(new BigDecimal("1100.00"));
        when(investimentoService.investir(investimentoRequestDto.getDocumentoCliente(), investimentoRequestDto.getValor())).thenReturn(contaInvestimento);

        ResponseEntity<SaldoResponseDto> response = operacoesBancariasController.investir(investimentoRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contaInvestimento.getNumero(), response.getBody().getNumeroConta());
        assertEquals(contaInvestimento.getSaldo(), response.getBody().getSaldo());
    }

    @Test
    void testAbrirContaPoupanca() {
        String cpf = "12345678900";

        // Mock return value
        var contaPoupanca = new Conta();
        contaPoupanca.setNumero(123L);
        contaPoupanca.setSaldo(BigDecimal.ZERO);
        when(contaService.abrirContaPoupanca(cpf)).thenReturn(contaPoupanca);

        ResponseEntity<SaldoResponseDto> response = operacoesBancariasController.abrirContaPoupanca(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contaPoupanca.getNumero(), response.getBody().getNumeroConta());
        assertEquals(contaPoupanca.getSaldo(), response.getBody().getSaldo());
    }
}
