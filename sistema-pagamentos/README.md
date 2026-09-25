# Sistema de Pagamentos Bancário

Entregável do **Desafio Final — Desenvolvimento em Java Backend** (Programação Orientada a Objetos).
Autor: **Matheus Vitor Carneiro Lira**

## Descrição

Núcleo de um sistema de pagamentos para o aplicativo de um banco. Toda forma de
pagamento tem um valor a ser cobrado, que **não pode ser zero ou negativo**.

Há uma classe base **abstrata** (`FormaPagamento`), que **não pode ser instanciada
diretamente**, e três tipos que herdam dela: **Pix**, **Débito** e **Crédito**.
Cada tipo processa o pagamento (`pagar()`) e mostra os detalhes (`exibirDetalhes()`)
à sua maneira.

## Conceitos de POO aplicados

| Conceito | Onde aparece |
|----------|--------------|
| **Abstração** | `FormaPagamento` é `abstract` (não instanciável) e define métodos abstratos |
| **Herança** | `Pix`, `Debito` e `Credito` estendem `FormaPagamento` |
| **Polimorfismo** | `pagar()` e `exibirDetalhes()` têm comportamento próprio em cada tipo; chamados via array da classe base |
| **Encapsulamento** | Atributos `private`, acessados por métodos controlados (getters/setters) |
| **Validação** | Valor `<= 0` é recusado antes de processar |
| **Enum** | `StatusPagamento` (PENDENTE, APROVADO, RECUSADO) |

## Regras de cada tipo (`pagar()`)

- **Pix** → aprovado na hora (se o valor for válido).
- **Débito** → só aprova se houver **saldo suficiente** na conta.
- **Crédito** → só aprova se o valor couber no **limite**, e divide em parcelas.

Em todos, se o valor for **zero ou negativo**, o pagamento é **recusado** com aviso no console.

## Estrutura do projeto

```
sistema-pagamentos/
├─ pom.xml                 # projeto Maven (importa direto no IntelliJ)
├─ README.md
├─ .gitignore
└─ src/main/java/com/matheus/pagamentos/
   ├─ StatusPagamento.java  # enum de status
   ├─ FormaPagamento.java   # classe base ABSTRATA
   ├─ Pix.java              # herda de FormaPagamento
   ├─ Debito.java           # herda de FormaPagamento
   ├─ Credito.java          # herda de FormaPagamento
   └─ Main.java             # cria um objeto de cada tipo e chama pagar()
```

## Como executar

### Opção 1 — IntelliJ IDEA (recomendado)
1. `File → Open…` e selecione a pasta `sistema-pagamentos`
2. O IntelliJ reconhece o `pom.xml` automaticamente
3. Abra `Main.java` e clique no ▶ (Run) ao lado do `main`

### Opção 2 — Linha de comando (JDK 17+)
Na pasta do projeto:

```bash
javac -d target/classes src/main/java/com/matheus/pagamentos/*.java
java -cp target/classes com.matheus.pagamentos.Main
```

## Saída esperada

```
=== Sistema de Pagamentos Bancario ===

Pagamento #1
PIX de R$ 150.00 aprovado na hora para a chave matheus@email.com.
[PIX] Chave: matheus@email.com | Valor: R$ 150.00 | Status: APROVADO

Pagamento #2
DEBITO de R$ 200.00 aprovado. Saldo restante: R$ 300.00.
[DEBITO] Valor: R$ 200.00 | Saldo da conta: R$ 300.00 | Status: APROVADO

Pagamento #3
CREDITO de R$ 1200.00 aprovado em 3x de R$ 400.00.
[CREDITO] Valor: R$ 1200.00 | Limite: R$ 3000.00 | Parcelas: 3x | Status: APROVADO

Pagamento #4
DEBITO recusado: saldo insuficiente (saldo R$ 300.00 para um pagamento de R$ 800.00).
[DEBITO] Valor: R$ 800.00 | Saldo da conta: R$ 300.00 | Status: RECUSADO

Pagamento #5
CREDITO recusado: valor acima do limite disponivel (R$ 3000.00).
[CREDITO] Valor: R$ 5000.00 | Limite: R$ 3000.00 | Parcelas: 6x | Status: RECUSADO

Pagamento #6
Pagamento recusado: o valor R$ -50.00 deve ser maior que zero.
[PIX] Chave: chave@invalida | Valor: R$ -50.00 | Status: RECUSADO
```
