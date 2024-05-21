package com.bancoeconomico;

import com.bancoeconomico.service.csv.CsvDataImporter;
import com.bancoeconomico.service.csv.AccountInitializationService;
import com.bancoeconomico.service.csv.CsvExporter;
import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.Conta;
import com.bancoeconomico.service.factory.OperacoesBancariasFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AdaBackendApplication implements CommandLineRunner {

	@Autowired
	private CsvDataImporter importer;

	@Autowired
	private AccountInitializationService accountService;

	@Autowired
	private CsvExporter exporter;

	public static void main(String[] args) {
		SpringApplication.run(AdaBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		File file = new File("pessoas.csv");

		if (file.exists() && !file.isDirectory()) {
			Optional<List<Cliente>> optionalClients = importer.importClients("pessoas.csv");

			optionalClients.ifPresent(clients -> {
				accountService.initializeAccounts(clients);
				exporter.exportClients(clients, "acao_marketing.csv");
			});
		} else {
			System.out.println("pessoas.csv not found. Skipping account initialization.");
		}
	}

	void deposito(Cliente cliente, BigDecimal valor) {
		Conta conta = cliente.getContas().get(0);
		OperacoesBancariasFactory.getInstance().get(cliente)
				.depositar(cliente, conta.getNumero(), valor);
		print("deposito: " + valor + " saldo " + conta.getSaldo());
	}

	void saque(Cliente cliente, BigDecimal valor) {
		Conta conta = cliente.getContas().get(0);
		OperacoesBancariasFactory.getInstance().get(cliente)
				.sacar(cliente, conta.getNumero(), valor);
		print("saque: " + valor + " saldo " + conta.getSaldo());
	}

	void transferir(Cliente clienteOrigem, Cliente clienteDestino, BigDecimal valor) {
		Conta contaOrigem = clienteOrigem.getContas().get(0);
		Conta contaDestino = clienteDestino.getContas().get(0);
		OperacoesBancariasFactory.getInstance().get(clienteOrigem)
				.transferir(clienteOrigem, contaOrigem.getNumero(), contaDestino, valor);
		print("transferencia origem: " + valor + " saldo " + contaOrigem.getSaldo());
		print("transferencia destino: " + valor + " saldo " + contaDestino.getSaldo());
	}

	void investimento(Cliente cliente, BigDecimal valor) {
		Conta conta = cliente.getContas().get(0);
		OperacoesBancariasFactory.getInstance().get(cliente)
				.investir(cliente, valor);
		print("investimento: " + valor + " saldo " + conta.getSaldo());
	}

	void imprimirTodosSaldos(Cliente cliente) {
		print("SALDOS ===============");
		print("Cliente: " + cliente.getNome());
		BigDecimal saldoTotal = BigDecimal.ZERO;
		for (Conta conta : cliente.getContas()) {
			print(conta.getClass().getSimpleName() + " - " + conta.getSaldo());
			saldoTotal = saldoTotal.add(conta.getSaldo());
		}
		print("Total: " + saldoTotal);
		print("SALDOS ===============");
	}

	void print(Object o) {
		System.out.println(o);
	}
}
