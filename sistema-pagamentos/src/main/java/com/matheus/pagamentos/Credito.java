package com.matheus.pagamentos;

/**
 * Pagamento via CREDITO: so aprova se o valor couber no limite disponivel,
 * e permite dividir em parcelas. Herda de FormaPagamento (polimorfismo no pagar()).
 */
public class Credito extends FormaPagamento {

    private double limite;  // limite disponivel no cartao
    private int parcelas;   // numero de parcelas

    public Credito(double valor, double limite, int parcelas) {
        super(valor);
        this.limite = limite;
        this.parcelas = parcelas;
    }

    @Override
    public void pagar() {
        if (!valorEhValido()) {
            return;
        }
        if (getValor() <= limite) {
            setStatus(StatusPagamento.APROVADO);
            double valorParcela = getValor() / parcelas;
            System.out.println("CREDITO de " + moeda(getValor()) + " aprovado em "
                    + parcelas + "x de " + moeda(valorParcela) + ".");
        } else {
            setStatus(StatusPagamento.RECUSADO);
            System.out.println("CREDITO recusado: valor acima do limite disponivel ("
                    + moeda(limite) + ").");
        }
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("[CREDITO] Valor: " + moeda(getValor())
                + " | Limite: " + moeda(limite)
                + " | Parcelas: " + parcelas + "x"
                + " | Status: " + getStatus());
    }
}
