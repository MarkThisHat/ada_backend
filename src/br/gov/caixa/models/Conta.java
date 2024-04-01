package br.gov.caixa.models;

import br.gov.caixa.enums.Status;
import br.gov.caixa.enums.TipoAcao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Conta {
    protected String id;
    protected double saldo;
    protected Set<TipoAcao> acoesPermitidas;
    protected List<Acao> historicoAcoes;
    protected LocalDate dataAtualizacao;
    protected Status status;
    protected Usuario titular;

    public Conta (String id, LocalDate data, Usuario titular) {
        this.id = id;
        this.saldo = 0;
        this.historicoAcoes = new ArrayList<>();
        this.dataAtualizacao = data;
        this.status = Status.ATIVO;
        this.titular = titular;
    }

    abstract Conta abreConta(String id, LocalDate data, Usuario usuario);

    public void adicionaAcao(Acao acao) {
        this.historicoAcoes.add(acao);
    }

    public boolean saque(double valor) {
        valor *= acrescimoPj(titular.getTipo());

        if (saldo < valor) {
            return false;
        }
        saldo -= valor;
        return true;
    }

    public boolean transferencia(double valor, Conta contaDestino, String destinatario) {
        double valorADebitar = valor;

        valorADebitar *= acrescimoPj(titular.getTipo());

        if (saldo < valorADebitar || !contaDestino.getTitular().equals(destinatario)) {
            return false;
        }
        saldo -= valorADebitar;
        contaDestino.recebeTransferencia(valor);
        return true;
    }

    public boolean recebeTransferencia(double valor) {
        saldo += valor;
        return true;
    }

    public double acrescimoPj(String titular) {
        if (titular.equals("PJ")) {
            return 1.05;
        }
        return 1;
    }

    public Usuario getTitular() {
        return this.titular;
    }
}