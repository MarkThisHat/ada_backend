package br.gov.caixa.models;

import br.gov.caixa.enums.Classificacao;
import br.gov.caixa.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private Classificacao classificacao;
    private String nome;
    private LocalDate dataDeCadastro;
    private Status status;
    private List<Conta> contas;

    public Usuario(String id, Classificacao classificacao, String nome) {
        this(id, classificacao, nome, LocalDate.now());
    }

    public Usuario(String id, Classificacao classificacao, String nome, LocalDate dataDeCadastro) {
        this.id = id;
        this.classificacao = classificacao;
        this.nome = nome;
        this.dataDeCadastro = dataDeCadastro;
        this.status = Status.ATIVO;
        this.contas = new ArrayList<Conta>();
//        this.adicionaConta(novaContaCorrente());
    }

    public void adicionaConta(Conta conta) {
        this.contas.add(conta);
    }

    public void abreContaCorrente() {
        ContaCorrente novaConta = new ContaCorrente(makeId(), LocalDate.now(), this);
        adicionaConta(novaConta);
    }

    public void abreContaPoupanca() {
        ContaPoupanca novaConta = new ContaPoupanca(makeId(), LocalDate.now(), this);
        adicionaConta(novaConta);
    }

    public void abreContaInvestimento() {
        ContaInvestimento novaConta = new ContaInvestimento(makeId(), LocalDate.now(), this);
        adicionaConta(novaConta);
    }

    public String makeId() {
        return this.id + this.contas.size();
    }

    public String getTipo() {
        return classificacao.tipoDeCliente();
    }

    public String getId() {
        return this.id;
    }
}
