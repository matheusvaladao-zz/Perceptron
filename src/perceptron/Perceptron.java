/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptron;

/**
 * Classe que implementa um neorônio do tipo Perceptron para simular uma porta
 * AND e faz o seu treinamento.
 *
 * @author Matheus
 */
public class Perceptron {

    // Obtem os pesos para as duas entradas e o BIAS.
    private double[] pesos = new double[3];

    // Somatório.
    private double soma = 0;

    // Número de épocas.
    private final int maximoEpocas = 50;

    // Contador de épocas durante o treinamento.
    private int contador = 0;

    // Vetor da matriz de aprendizado.
    private int[][] matrizAprendizado = new int[4][3];

    //taxa de aprendizado
    private double taxaAprendizado = 0.2;

    /**
     * Retorna contador de épocas.
     *
     * @return
     */
    public int getContador() {
        return this.contador;
    }

    /**
     * Construtor da classe.
     */
    public Perceptron() {

        // Inicializando valores para treinamento da porta AND.
        this.matrizAprendizado[0][0] = 0;
        this.matrizAprendizado[0][1] = 0;
        this.matrizAprendizado[0][2] = 0;

        this.matrizAprendizado[1][0] = 0;
        this.matrizAprendizado[1][1] = 1;
        this.matrizAprendizado[1][2] = 0;

        this.matrizAprendizado[2][0] = 1;
        this.matrizAprendizado[2][1] = 0;
        this.matrizAprendizado[2][2] = 0;

        this.matrizAprendizado[3][0] = 1;
        this.matrizAprendizado[3][1] = 1;
        this.matrizAprendizado[3][2] = 1;

        //Inicializando pesos: entradas e BIAS.
        pesos[0] = 0;
        pesos[1] = 0;
        pesos[2] = 0;

    }

    /**
     * Método responsável por fazer o somatório e calcular a função de ativação.
     *
     * @param x
     * @param y
     * @return
     */
    public int executar(int x, int y) {

        soma = (x * pesos[0]) + (y * pesos[1]) + ((-1) * pesos[2]);

        // Função de Ativação
        if (soma > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Método de treinamento da rede.
     */
    public void treinar() {

        boolean treinou = true;
        int saida;

        for (int i = 0; i < 4; i++) {

            // A saída recebe o resultado da execução
            saida = executar(matrizAprendizado[i][0], matrizAprendizado[i][1]);

            if (saida != matrizAprendizado[i][2]) {
                corrigirPeso(i, saida);
                treinou = false;
            }
        }

        this.contador++;

        if ((treinou == false) && (this.contador < this.maximoEpocas)) {
            treinar();
        }
    }

    /**
     * Método que corrige os pesos durante o treinamento para que este se
     * aproxime da função desejada.
     *
     * @param i
     * @param saida
     */
    void corrigirPeso(int i, int saida) {
        pesos[0] = pesos[0] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0] * taxaAprendizado);
        pesos[1] = pesos[1] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1] * taxaAprendizado);
        pesos[2] = pesos[2] + (1 * (matrizAprendizado[i][2] - saida) * taxaAprendizado);
    }

}
