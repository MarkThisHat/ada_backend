package main.java.com.bancoeconomico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		Optional<List<Cliente>> optionalClients = importer.importClients("pessoas.csv");

		optionalClients.ifPresent(clients -> {
			accountService.initializeAccounts(clients);
			exporter.exportClients(clients, "acao_marketing.csv");

			// Example usage of other methods (comment out if not needed)
			if (!clients.isEmpty()) {
				Cliente cliente = clients.get(0);
				deposito(cliente, new BigDecimal("100.00"));
				saque(cliente, new BigDecimal("50.00"));
				imprimirTodosSaldos(cliente);
			}
		});
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
