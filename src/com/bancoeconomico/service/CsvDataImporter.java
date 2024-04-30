package com.bancoeconomico.service;

import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.ClientePF;
import com.bancoeconomico.model.ClientePJ;
import com.bancoeconomico.model.enums.TipoClienteEnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvDataImporter {

    public Optional<List<Cliente>> importClients(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Map<String, Integer> headerMap = readHeader(br);
            return Optional.of(br.lines()
                    .map(line -> line.split(","))
                    .filter(data -> isValidClient(data, headerMap))
                    .map(data -> createClient(data, headerMap))
                    .flatMap(Optional::stream)  // Converts Optional<Cliente> to Stream<Cliente>
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            return Optional.empty();
        }
    }

    private Map<String, Integer> readHeader(BufferedReader br) throws IOException {
        String[] headers = br.readLine().split(",");
        Map<String, Integer> headerMap = new HashMap<>();
        IntStream.range(0, headers.length)
                .forEach(i -> headerMap.put(headers[i], i));
        return headerMap;
    }

    private Optional<Cliente> createClient(String[] data, Map<String, Integer> headerMap) {
        String nome = data[headerMap.get("nome")];
        String documento = data[headerMap.get("documento")];
        TipoClienteEnum tipo = TipoClienteEnum.fromInt(Integer.parseInt(data[headerMap.get("tipo")]));

        switch (tipo) {
            case PESSOA_JURIDICA:
                return Optional.of(new ClientePJ(nome, documento));
            case PESSOA_FISICA:
                return Optional.of(new ClientePF(nome, documento));
            default:
                return Optional.empty();
        }
    }

    private boolean isValidClient(String[] data, Map<String, Integer> headerMap) {
        TipoClienteEnum tipo = TipoClienteEnum.fromInt(Integer.parseInt(data[headerMap.get("tipo")]));
        switch (tipo) {
            case PESSOA_JURIDICA:
                return true;
            case PESSOA_FISICA:
                return checkAge(data[headerMap.get("nascimento_criacao")]);
            default:
                return false;
        }
    }

    private boolean checkAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate birthDate = LocalDate.parse(birthdate, formatter);
            LocalDate now = LocalDate.now();
            int age = Period.between(birthDate, now).getYears();
            return age >= 18;
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return false;
        }
    }
}
