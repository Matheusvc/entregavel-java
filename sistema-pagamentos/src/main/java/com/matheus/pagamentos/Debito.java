package com.matheus.pagamentos;

/**
 * Pagamento via DEBITO: so aprova se houver saldo suficiente na conta.
 * Herda de FormaPagamento e tem sua propria regra no pagar() (polimorfismo).
 */
public class Debito extends FormaPagamento {

    private double saldoConta; // saldo disponivel na conta

    public Debito(double valor, double saldoConta) {
        super(valor);
        this.saldoConta = saldoConta;
    }

    @Override
    public void pagar() {
        if (!valorEhValido()) {
            return;
        }
        if (saldoConta >= getValor()) {
            saldoConta -= getValor();
            setStatus(StatusPagamento.APROVADO);
            System.out.println("DEBITO de " + moeda(getValor())
                    + " aprovado. Saldo restante: " + moeda(saldoConta) + ".");
        } else {
            setStatus(StatusPagamento.RECUSADO);
            System.out.println("DEBITO recusado: saldo insuficiente (saldo " + moeda(saldoConta)
                    + " para um pagamento de " + moeda(getValor()) + ").");
        }
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("[DEBITO] Valor: " + moeda(getValor())
                + " | Saldo da conta: " + moeda(saldoConta)
                + " | Status: " + getStatus());
    }
}
