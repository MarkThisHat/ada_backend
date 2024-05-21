package main.java.com.bancoeconomico.exceptions;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente!");
    }

}
