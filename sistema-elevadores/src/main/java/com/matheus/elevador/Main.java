package com.matheus.elevador;

/**
 * Classe principal: cria um objeto Elevador e demonstra no console os dois cenarios
 * pedidos no enunciado:
 *   1) uma subida bem-sucedida (peso dentro do limite);
 *   2) uma tentativa de subida bloqueada por excesso de peso.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Controle de Elevadores ===");
        System.out.println();

        // Cria um elevador no terreo (andar 0) com peso maximo de 500 kg
        Elevador elevador = new Elevador(0, 500);
        System.out.println("Elevador criado -> " + elevador);
        System.out.println();

        // ----- Cenario 1: subida bem-sucedida (peso dentro do limite) -----
        System.out.println("--- Cenario 1: peso dentro do limite ---");
        elevador.carregarPeso(300);
        System.out.println("Peso atual carregado: " + elevador.getPesoAtual() + " kg");
        elevador.subir(); // deve subir para o andar 1
        System.out.println();

        // ----- Cenario 2: subida bloqueada por excesso de peso -----
        System.out.println("--- Cenario 2: excesso de peso ---");
        elevador.carregarPeso(650);
        System.out.println("Peso atual carregado: " + elevador.getPesoAtual() + " kg");
        elevador.subir(); // deve ser bloqueado e continuar no andar 1
        System.out.println();

        // ----- Bonus: aliviando o peso, o elevador volta a se mover -----
        System.out.println("--- Bonus: peso reduzido, elevador volta a funcionar ---");
        elevador.carregarPeso(200);
        System.out.println("Peso atual carregado: " + elevador.getPesoAtual() + " kg");
        elevador.subir(); // sobe para o andar 2
        elevador.descer(); // desce para o andar 1
        System.out.println();

        System.out.println("Estado final -> " + elevador);
    }
}
