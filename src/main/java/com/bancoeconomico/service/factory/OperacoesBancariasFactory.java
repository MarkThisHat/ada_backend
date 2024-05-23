package com.bancoeconomico.service.factory;

import com.bancoeconomico.exceptions.TipoClienteInvalidoException;
import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.ClientePf;
import com.bancoeconomico.model.ClientePj;
import com.bancoeconomico.service.OperacoesBancarias;
import com.bancoeconomico.service.impl.OperacoesContaClientePF;
import com.bancoeconomico.service.impl.OperacoesContaClientePJ;

public final class OperacoesBancariasFactory {

    private final OperacoesBancarias<ClientePf> opeBancPF;
    private final OperacoesBancarias<ClientePj> opeBancPJ;

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
        if (cliente instanceof ClientePf) {
            return this.opeBancPF;
        } else if (cliente instanceof ClientePj) {
            return this.opeBancPJ;
        } else {
            throw new TipoClienteInvalidoException();
        }
    }

}
