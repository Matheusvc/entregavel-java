package com.matheus.pagamentos;

/**
 * Pagamento via PIX: aprovado na hora, desde que o valor seja valido.
 * Herda de FormaPagamento (heranca) e implementa pagar()/exibirDetalhes() (polimorfismo).
 */
public class Pix extends FormaPagamento {

    private String chave; // chave PIX de destino

    public Pix(double valor, String chave) {
        super(valor);
        this.chave = chave;
    }

    @Override
    public void pagar() {
        if (!valorEhValido()) {
            return; // valor zero/negativo -> ja foi recusado na validacao
        }
        setStatus(StatusPagamento.APROVADO);
        System.out.println("PIX de " + moeda(getValor()) + " aprovado na hora para a chave " + chave + ".");
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("[PIX] Chave: " + chave
                + " | Valor: " + moeda(getValor())
                + " | Status: " + getStatus());
    }
}
