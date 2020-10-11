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

    private double tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados;
    private Integer escolhaCenario = 0;
    private final Execute execute;

    public Start() {
        execute = new Execute();
    }

    public void interfaceUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.println("Informe o cenário para simulação \n"
                + "\t1 - 40% da capacidade"
                + "\t2 - 80% da capacidade"
                + "\t3 - 90% da capacidade"
                + "\t4 - 99% da capacidade"
                + "\t5 Deninir novo cenário");
        escolhaCenario = input.nextInt();
        cenarios(escolhaCenario);        
    }
    
    private void cenarioNaoDefinido(){
        Scanner input = new Scanner(System.in);
        
        System.out.printf("Informe o tempo medio entre a chegada de cada cliente ao caixa (em segundos): ");
        tempoMedioClientes = input.nextDouble();

        System.out.printf("Informe o tempo medio gasto para o atendimento do caixa para cada cliente (em segundos): ");
        tempoMedioAtendimento = input.nextDouble();

        System.out.printf("Informe o tempo total de simulacao (segundos): ");
        tempoSimulacao = input.nextDouble();

        System.out.printf("Informe o intervalo de tempo para o atendimento (segundos): ");
        intervaloDeTempoPlotarResultados = input.nextDouble();
        
        execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados);
    }

    public void cenarios(Integer simulacao) {
        tempoSimulacao = 10000.0;
        intervaloDeTempoPlotarResultados = 100.0;
        tempoMedioClientes = 1.00;
        switch (simulacao) {
            case 1://ocupação 40% 
                tempoMedioAtendimento = tempoMedioClientes * (0.40 * tempoSimulacao) / (tempoSimulacao);
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados);
                break;
            case 2://ocupação 80%
                tempoMedioAtendimento = tempoMedioClientes * (0.80 * tempoSimulacao) / (tempoSimulacao);
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados);
                break;
            case 3://ocupação 90%
                tempoMedioAtendimento = tempoMedioClientes * (0.90 * tempoSimulacao) / (tempoSimulacao);
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados);
                break;
            case 4://ocupação 99%
                tempoMedioAtendimento = tempoMedioClientes * (0.99 * tempoSimulacao) / (tempoSimulacao);
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempoPlotarResultados);
                break;
            default:
                cenarioNaoDefinido();
                break;
        }
    }
}
