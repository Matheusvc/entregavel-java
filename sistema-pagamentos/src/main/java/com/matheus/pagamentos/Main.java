package com.matheus.pagamentos;

/**
 * Classe principal: cria um objeto de cada tipo de pagamento e chama pagar()
 * em cada um, exibindo o resultado no console.
 *
 * Os objetos ficam num array do tipo da classe base (FormaPagamento). Ao chamar
 * pagar() e exibirDetalhes() no laco, o Java escolhe automaticamente a versao de
 * cada tipo -> isso e POLIMORFISMO.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Pagamentos Bancario ===");
        System.out.println();

        // Observacao: a linha abaixo NAO compila, pois FormaPagamento e abstrata:
        // FormaPagamento f = new FormaPagamento(100); // ERRO de compilacao

        FormaPagamento[] pagamentos = {
                new Pix(150.00, "matheus@email.com"),   // PIX valido -> aprovado
                new Debito(200.00, 500.00),             // saldo suficiente -> aprovado
                new Credito(1200.00, 3000.00, 3),       // dentro do limite -> aprovado em 3x
                new Debito(800.00, 300.00),             // saldo insuficiente -> recusado
                new Credito(5000.00, 3000.00, 6),       // acima do limite -> recusado
                new Pix(-50.00, "chave@invalida")       // valor invalido -> recusado
        };

        int numero = 1;
        for (FormaPagamento pagamento : pagamentos) {
            System.out.println("Pagamento #" + numero);
            pagamento.pagar();          // regra de negocio diferente por tipo
            pagamento.exibirDetalhes(); // exibicao diferente por tipo
            System.out.println();
            numero++;
        }
    }
}
