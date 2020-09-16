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

    private double tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempo;
    private final Execute execute;

    public Start() {
        execute = new Execute();
    }

    public void interfaceUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.printf("Informe o tempo medio entre a chegada de clientes (segundos): ");
        tempoMedioClientes = input.nextDouble();

        System.out.printf("Informe o tempo medio gasto para atender cada cliente (segundos): ");
        tempoMedioAtendimento = input.nextDouble();

        System.out.printf("Informe o tempo total de simulacao (segundos): ");
        tempoSimulacao = input.nextDouble();

        execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempo);
    }

    public void cenarios(int simulacao) {
        tempoSimulacao = 100000.0;
        intervaloDeTempo = 100.0;      
        switch (simulacao) {
            case 40://ocupação 40% //TODO alterar parametros
                tempoMedioClientes = 0.25;
                tempoMedioAtendimento = 0.15;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempo);
                break;
            case 80://ocupação 80%
                tempoMedioClientes = 2.00;
                tempoMedioAtendimento = 1.60;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempo);
                break;
            case 90://ocupação 90%
                tempoMedioClientes = 0.50;
                tempoMedioAtendimento = 0.45;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempo);
                break;
            case 99://ocupação 99%
                tempoMedioClientes = 3.00;
                tempoMedioAtendimento = 2.97;
                execute.simulacao(tempoMedioClientes, tempoMedioAtendimento, tempoSimulacao, intervaloDeTempo);
                break;
            default:
                System.out.printf("\t\tERRO! \n\tEsta simulação não foi pré definida !");
                break;
        }
    }
}
