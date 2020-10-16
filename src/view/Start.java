/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Execute;
import java.util.Scanner;

/**
 *
 * @author Vinicius_2
 */
public class Start {

    private double tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados, relacao;
    private Integer escolhaCenario, qtdCaixas;
    private final Execute execute;

    public Start() {
        intervaloDeTempoPlotarResultados = 600.0;
        tempoSimulacao = 1200000.0;
        execute = new Execute();
    }

    public void interfaceUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.println("Informe o cenário para simulação \n"
                + "\t1 - 40% da capacidade\n"
                + "\t2 - 80% da capacidade\n"
                + "\t3 - 90% da capacidade\n"
                + "\t4 - 99% da capacidade\n"
                + "\t5 - Deninir novo cenário\n"
                + "\t6 - SAIR\n");
        escolhaCenario = input.nextInt();
        cenarios(escolhaCenario);
    }

    private void cenarioNaoDefinido() {
        Scanner input = new Scanner(System.in);
        
        System.out.printf("Informe o relação entre chegada e a capacidade total de atendimento [un. decimal]: ");
        relacao = input.nextDouble() / 100;

        System.out.printf("Informe a quantidade de caixas disponiveis [un. decimal]: ");
        qtdCaixas = input.nextInt();

        System.out.printf("Informe o tempo médio de atendimento [un. segundos]: ");
        tempoMedioAtendimento = qtdCaixas / input.nextDouble();

        System.out.printf("Informe o tempo total de simulacao [un. minutos]: ");
        tempoSimulacao = input.nextDouble() * 60000;
        
        System.out.printf("Informe o intervalo de tempo para apresentar os resultados de E[N] e W[N] menor que %.2f: ", tempoSimulacao);
        intervaloDeTempoPlotarResultados = input.nextDouble() * 60000;

        tempoMedioClientes = relacao * tempoMedioAtendimento;
        execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados, qtdCaixas);
    }

    public void cenarios(Integer simulacao) {
        qtdCaixas = 10;
        switch (simulacao) {
            case 1://ocupação 40% 
                tempoMedioAtendimento = qtdCaixas / 60.0;
                tempoMedioClientes = 0.4 * tempoMedioAtendimento;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados, qtdCaixas);
                break;
            case 2://ocupação 80%
                tempoMedioAtendimento = qtdCaixas / 60.0;
                tempoMedioClientes = 0.8 * tempoMedioAtendimento;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados, qtdCaixas);
                break;
            case 3://ocupação 90%
                tempoMedioAtendimento = qtdCaixas / 60.0;
                tempoMedioClientes = 0.9 * tempoMedioAtendimento;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados, qtdCaixas);
                break;
            case 4://ocupação 99%
                tempoMedioAtendimento = qtdCaixas / 60.0;
                tempoMedioClientes = 0.99 * tempoMedioAtendimento;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados, qtdCaixas);
                break;
            case 5:
                cenarioNaoDefinido();
                break;
            case 6:
                break;
            default:
                System.out.println("Opção Inválida\n");
                interfaceUsuario();
                break;
        }
    }
}
