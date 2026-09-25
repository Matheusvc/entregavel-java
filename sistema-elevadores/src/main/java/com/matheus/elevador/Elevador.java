package com.matheus.elevador;

/**
 * Sistema de Controle de Elevadores - Semana 03 (Programacao Orientada a Objetos)
 * Autor: Matheus Vitor Carneiro Lira
 *
 * A classe Elevador aplica ENCAPSULAMENTO: os atributos sao privados e so podem
 * ser alterados atraves de metodos da propria classe, nunca diretamente de fora.
 */
public class Elevador {

    // Atributos privados: ninguem de fora altera diretamente (encapsulamento)
    private int andarAtual;        // andar em que o elevador esta
    private final int pesoMaximo;  // peso maximo suportado (kg) - definido na criacao e nao muda
    private double pesoAtual;      // peso que esta dentro do elevador agora (kg)

    /**
     * Cria um elevador informando o andar inicial e o peso maximo suportado (kg).
     */
    public Elevador(int andarInicial, int pesoMaximo) {
        this.andarAtual = andarInicial;
        this.pesoMaximo = pesoMaximo;
        this.pesoAtual = 0;
    }

    /**
     * Metodo controlado para definir o peso atual dentro do elevador.
     * Nao permite peso negativo (validacao).
     */
    public void carregarPeso(double pesoAtual) {
        if (pesoAtual < 0) {
            System.out.println("Peso invalido: nao e possivel carregar um peso negativo.");
            return;
        }
        this.pesoAtual = pesoAtual;
    }

    /**
     * Sobe um andar.
     * Antes de subir, verifica se o peso atual ultrapassa o peso maximo:
     *  - se ultrapassar, NAO se move e avisa por excesso de peso;
     *  - se estiver dentro do limite, sobe e mostra o andar em que parou.
     */
    public void subir() {
        if (excedeuPesoMaximo()) {
            System.out.println("EXCESSO DE PESO! Peso atual (" + pesoAtual + " kg) maior que o maximo ("
                    + pesoMaximo + " kg). O elevador NAO vai sair do andar " + andarAtual + ".");
        } else {
            andarAtual++;
            System.out.println("O elevador subiu e parou no andar " + andarAtual + ".");
        }
    }

    /**
     * Desce um andar (metodo complementar), respeitando o mesmo controle de peso
     * e sem descer abaixo do terreo (andar 0).
     */
    public void descer() {
        if (excedeuPesoMaximo()) {
            System.out.println("EXCESSO DE PESO! O elevador NAO vai sair do andar " + andarAtual + ".");
        } else if (andarAtual <= 0) {
            System.out.println("O elevador ja esta no andar terreo (0) e nao pode descer mais.");
        } else {
            andarAtual--;
            System.out.println("O elevador desceu e parou no andar " + andarAtual + ".");
        }
    }

    /**
     * Regra de negocio isolada num metodo privado para deixar o codigo mais legivel
     * e reaproveitavel dentro da classe.
     */
    private boolean excedeuPesoMaximo() {
        return pesoAtual > pesoMaximo;
    }

    // Getters: leitura controlada dos atributos privados
    public int getAndarAtual() {
        return andarAtual;
    }

    public int getPesoMaximo() {
        return pesoMaximo;
    }

    public double getPesoAtual() {
        return pesoAtual;
    }

    @Override
    public String toString() {
        return "Elevador [andarAtual=" + andarAtual + ", pesoAtual=" + pesoAtual
                + " kg, pesoMaximo=" + pesoMaximo + " kg]";
    }
}
