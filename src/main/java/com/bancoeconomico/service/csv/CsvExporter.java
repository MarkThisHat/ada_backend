package main.java.com.bancoeconomico.service.csv;

import main.java.com.bancoeconomico.model.Cliente;
import main.java.com.bancoeconomico.model.ClientePF;
import main.java.com.bancoeconomico.model.Conta;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CsvExporter {

    public void exportClients(List<Cliente> clients, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nome,Documento,Tipo,NumeroConta,Saldo\n");

            for (Cliente client : clients) {
                for (Conta conta : client.getContas()) {
                    String line = String.format("%s,%s,%s,%d,%s\n",
                            client.getNome(),
                            client.getId(),
                            client instanceof ClientePF ? "PF" : "PJ",
                            conta.getNumero(),
                            conta.getSaldo().toString());
                    writer.write(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
