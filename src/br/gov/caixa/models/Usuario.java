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
        this.contas = new ArrayList<>();
    }

    public void adicionaConta(Conta conta) {
        this.contas.add(conta);
    }

    public void abreContaCorrente(FabricaDeContas fabrica) {
        ContaCorrente novaConta = fabrica.criarContaCorrente(this);
        adicionaConta(novaConta);
    }

    public void abreContaPoupanca(FabricaDeContas fabrica) {
        ContaPoupanca novaConta = fabrica.criarContaPoupanca(this);
        adicionaConta(novaConta);
    }

    public void abreContaInvestimento(FabricaDeContas fabrica) {
        ContaInvestimento novaConta = fabrica.criarContaInvestimento(this);
        adicionaConta(novaConta);
    }

    public String makeId() {
        return this.id + "-" + (this.contas.size() + 1);
    }

    public Classificacao getClassificacao() {
        return this.classificacao;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public LocalDate getDataDeCadastro() {
        return this.dataDeCadastro;
    }

    public Status getStatus() {
        return this.status;
    }

    public List<Conta> getContas() {
        return new ArrayList<>(this.contas);
    }
}
