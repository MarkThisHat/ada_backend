package br.gov.caixa.models;

import br.gov.caixa.enums.Classificacao;
import br.gov.caixa.servicos.tarifas.EstrategiaDeCalculoDeTarifas;
import br.gov.caixa.servicos.tarifas.EstrategiaDeTarifasPessoaFisica;
import br.gov.caixa.servicos.tarifas.EstrategiaDeTarifasPessoaJuridica;

import java.time.LocalDate;

public class FabricaDeContas {

    public ContaCorrente criarContaCorrente(Usuario usuario) {
        ContaCorrente contaCorrente = new ContaCorrente(usuario.makeId(), LocalDate.now(), usuario);
        configurarEstrategias(contaCorrente, usuario.getClassificacao());
        return contaCorrente;
    }

    public ContaPoupanca criarContaPoupanca(Usuario usuario) {
        return new ContaPoupanca(usuario.makeId(), LocalDate.now(), usuario);
    }

    public ContaInvestimento criarContaInvestimento(Usuario usuario) {
        return new ContaInvestimento(usuario.makeId(), LocalDate.now(), usuario);
    }

    private void configurarEstrategias(Conta conta, Classificacao classificacao) {
        EstrategiaDeCalculoDeTarifas estrategia = determinarEstrategiaDeTarifas(classificacao);
        conta.setEstrategiaDeCalculoDeTarifas(estrategia);
    }

    private EstrategiaDeCalculoDeTarifas determinarEstrategiaDeTarifas(Classificacao classificacao) {
        switch (classificacao) {
            case CPF:
                return new EstrategiaDeTarifasPessoaFisica();
            case CNPJ:
                return new EstrategiaDeTarifasPessoaJuridica();
            default:
                throw new IllegalArgumentException("Classificação desconhecida: " + classificacao);
        }
    }
}
