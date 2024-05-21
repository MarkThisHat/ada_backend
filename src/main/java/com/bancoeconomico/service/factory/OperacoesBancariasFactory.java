package main.java.com.bancoeconomico.service.factory;

import main.java.com.bancoeconomico.exceptions.TipoClienteInvalidoException;
import main.java.com.bancoeconomico.model.Cliente;
import main.java.com.bancoeconomico.model.ClientePF;
import main.java.com.bancoeconomico.model.ClientePJ;
import main.java.com.bancoeconomico.service.OperacoesBancarias;
import main.java.com.bancoeconomico.service.impl.OperacoesContaClientePF;
import main.java.com.bancoeconomico.service.impl.OperacoesContaClientePJ;

public final class OperacoesBancariasFactory {

    private final OperacoesBancarias<ClientePF> opeBancPF;
    private final OperacoesBancarias<ClientePJ> opeBancPJ;

    private static OperacoesBancariasFactory instance;

    private OperacoesBancariasFactory() {
        this.opeBancPF = new OperacoesContaClientePF();
        this.opeBancPJ = new OperacoesContaClientePJ();
    }

    public static OperacoesBancariasFactory getInstance() {
        if (instance == null) {
            instance = new OperacoesBancariasFactory();
        }
        return instance;
    }

    public OperacoesBancarias get(Cliente cliente) {
        if (cliente instanceof ClientePF) {
            return this.opeBancPF;
        } else if (cliente instanceof ClientePJ) {
            return this.opeBancPJ;
        } else {
            throw new TipoClienteInvalidoException();
        }
    }

}
