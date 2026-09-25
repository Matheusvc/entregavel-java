# Sistema de Controle de Elevadores

Entregável da **Semana 03 — Desenvolvimento em Java Backend** (Programação Orientada a Objetos).
Autor: **Matheus Vitor Carneiro Lira**

## Descrição

Sistema de controle de elevadores para uma empresa de manutenção predial, aplicando
conceitos de **POO** — principalmente **encapsulamento**.

A classe `Elevador` guarda o andar atual e o peso máximo suportado. Esses dados **não
podem ser alterados livremente de fora da classe**: são atributos `private` e só mudam
através de métodos que controlam essas alterações.

## Regras do elevador (método `subir()`)

Antes de subir, o elevador verifica o peso:

- **Peso dentro do limite** → sobe um andar e mostra o andar em que parou.
- **Peso acima do máximo** → **não se move** e avisa no console que não vai sair do andar atual por excesso de peso.

## Conceitos de POO aplicados

| Conceito | Onde aparece |
|----------|--------------|
| **Encapsulamento** | Atributos `private` (`andarAtual`, `pesoMaximo`, `pesoAtual`) |
| **Métodos de acesso controlado** | `carregarPeso()`, `subir()`, `descer()` e os *getters* |
| **Método privado (coesão)** | `excedeuPesoMaximo()` isola a regra de negócio |
| **Construtor** | Inicializa o elevador com andar inicial e peso máximo |
| **Imutabilidade parcial** | `pesoMaximo` é `final` (definido na criação e não muda) |
| **`toString()`** | Sobrescrito para exibir o estado do objeto |

## Estrutura do projeto

```
sistema-elevadores/
├─ pom.xml                 # projeto Maven (importa direto no IntelliJ)
├─ README.md
├─ .gitignore
└─ src/
   └─ main/
      └─ java/
         └─ com/matheus/elevador/
            ├─ Elevador.java   # classe com atributos privados e o método subir()
            └─ Main.java       # cria o elevador e demonstra os cenários
```

## Como executar

### Opção 1 — IntelliJ IDEA (recomendado)
1. `File → Open…` e selecione a pasta `sistema-elevadores`
2. O IntelliJ reconhece o `pom.xml` automaticamente
3. Abra `Main.java` e clique no ▶ (Run) ao lado do `main`

### Opção 2 — Linha de comando (JDK 17+)
Na pasta do projeto:

```bash
javac -d target/classes src/main/java/com/matheus/elevador/*.java
java -cp target/classes com.matheus.elevador.Main
```

## Saída esperada

```
=== Sistema de Controle de Elevadores ===

Elevador criado -> Elevador [andarAtual=0, pesoAtual=0.0 kg, pesoMaximo=500 kg]

--- Cenario 1: peso dentro do limite ---
Peso atual carregado: 300.0 kg
O elevador subiu e parou no andar 1.

--- Cenario 2: excesso de peso ---
Peso atual carregado: 650.0 kg
EXCESSO DE PESO! Peso atual (650.0 kg) maior que o maximo (500 kg). O elevador NAO vai sair do andar 1.

--- Bonus: peso reduzido, elevador volta a funcionar ---
Peso atual carregado: 200.0 kg
O elevador subiu e parou no andar 2.
O elevador desceu e parou no andar 1.

Estado final -> Elevador [andarAtual=1, pesoAtual=200.0 kg, pesoMaximo=500 kg]
```
