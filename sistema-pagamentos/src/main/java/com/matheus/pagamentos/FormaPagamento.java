package com.matheus.pagamentos;

import java.util.Locale;

/**
 * Sistema de Pagamentos Bancario - Desafio Final (Programacao Orientada a Objetos)
 * Autor: Matheus Vitor Carneiro Lira
 *
 * Classe base ABSTRATA que representa uma forma de pagamento.
 * - E abstrata: NAO pode ser instanciada diretamente (new FormaPagamento(...) nao compila).
 * - Aplica ENCAPSULAMENTO: atributos privados, acessados por metodos controlados.
 * - Define o contrato (metodos abstratos pagar() e exibirDetalhes()) que cada
 *   tipo de pagamento implementa a sua maneira (POLIMORFISMO).
 */
public abstract class FormaPagamento {

    private double valor;             // valor a ser cobrado
    private StatusPagamento status;   // situacao atual do pagamento

    public FormaPagamento(double valor) {
        this.valor = valor;
        this.status = StatusPagamento.PENDENTE;
    }

    /** Cada tipo de pagamento processa de forma diferente. */
    public abstract void pagar();

    /** Cada tipo exibe seus detalhes de forma diferente. */
    public abstract void exibirDetalhes();

    /**
     * Validacao comum a todos os pagamentos: o valor nao pode ser zero ou negativo.
     * Se for invalido, recusa o pagamento e avisa no console.
     */
    protected boolean valorEhValido() {
        if (valor <= 0) {
            System.out.println("Pagamento recusado: o valor " + moeda(valor) + " deve ser maior que zero.");
            status = StatusPagamento.RECUSADO;
            return false;
        }
        return true;
    }

    /** Formata um valor como moeda (ex.: R$ 150.00), de forma independente do idioma do sistema. */
    protected String moeda(double v) {
        return String.format(Locale.US, "R$ %.2f", v);
    }

    // Acesso controlado aos atributos (encapsulamento)
    public double getValor() {
        return valor;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    protected void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
