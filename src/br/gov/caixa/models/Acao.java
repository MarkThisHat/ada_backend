package br.gov.caixa.models;

import br.gov.caixa.enums.TipoAcao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Acao {
    private LocalDateTime data;
    private TipoAcao acao;
    private double valorPretendido;
    private double valorReal;
    private String usuarioOrigem;
    private String usuarioDestino;
    private String obs;

    public Acao(LocalDateTime data, TipoAcao acao, double valorPretendido, double valorReal, String usuarioOrigem, String usuarioDestino, String obs) {
        this.data = data;
        this.acao = acao;
        this.valorPretendido = valorPretendido;
        this.valorReal = valorReal;
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
        this.obs = obs;
    }
}
