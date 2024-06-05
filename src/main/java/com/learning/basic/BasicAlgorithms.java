package com.learning.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BasicAlgorithms {

    public boolean isEven(final int number) {
        return number % 2 == 0;
    }

    public boolean isPrimo(final int n) {
        int fator = 2;

        if (n <= 1) {
            return false;
        }
        while (fator * fator <= n) {
            if (n % fator == 0) {
                return false;
            }
            fator = fator + 1;
        }
        return true;
    }

    public static int calculaFatorial(final Integer numero) {

        Objects.requireNonNull(numero, "Não é permitido a passagem de numero nulo");

        if (numero < 0) {
            throw new RuntimeException("Não é permitido a passagem de numero negativo");
        }
        if (numero > 12) {
            throw new RuntimeException("Numero é maior que 12");
        }

        int aux = 1;

        for (int i = numero; i > 0; i--) {
            aux = aux * i;
        }

        return aux;
    }

    public int contador(final int[] vetor, final int valor) {
        int quant = 0;

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == valor) {
                quant++;
            }
        }
        return quant;
    }


    public static int pesquisaLinear(List<Integer> list, int num){
        for (int i = 0; i < list.size() ; i++) {
            if (num == list.get(i)) {
                return i;
            }
        }

        return -1;
    }

    public static String inversao(final String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static boolean isAnagram(String palavra1, String palavra2) {
        Map<Character, Integer> contador1 = new HashMap<>();
        Map<Character, Integer> contador2 = new HashMap<>();

        //palavra 1
        for(Character c: palavra1.toCharArray()) {
            contador1.put(c, contador1.getOrDefault(c, 0) + 1);
        }

        //palavra 2
        for(Character c: palavra2.toCharArray()) {
            contador2.put(c, contador2.getOrDefault(c, 0) + 1);
        }

        return contador1.equals(contador2);
    }
}




