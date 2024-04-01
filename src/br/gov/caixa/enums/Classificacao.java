package br.gov.caixa.enums;

public enum Classificacao {
    CPF, CNPJ;

    public String tipoDeCliente() {
        switch (this) {
            case CPF:
                return "PF";
            case CNPJ:
                return "PJ";
            default:
                return "Não Classificado";
        }
    }
}
