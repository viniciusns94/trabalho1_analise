/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.lang.Math.log;
import model.Info;

/**
 *
 * @author Vinicius_2
 */
public class Execute {

    private double tempo, chegadaCliente, saidaAtendimento, somaAtendimentos, ocupacao;
    private Integer fila;
    private final Controller controller;
    private final Info eN, eWEntrada, eWSaida;

    public Execute() {
        this.controller = new Controller();
        this.saidaAtendimento = 0.0;
        this.fila = 0;
        this.somaAtendimentos = 0.0;
        this.eN = new Info(0.0, 0.0, 0.0);
        this.eWEntrada = new Info(0.0, 0.0, 0.0);
        this.eWSaida = new Info(0.0, 0.0, 0.0);
    }

    public void start(Double tempoMedioClientes, Double tempoMedioAtendimento, Double tempoSimulacao) {
        tempoMedioClientes = 1.0 / tempoMedioClientes;
        tempoMedioAtendimento = 1.0 / tempoMedioAtendimento;
        chegadaCliente = (-1.0 / tempoMedioClientes) * log(controller.aleatorio());

        while (tempo <= tempoSimulacao) {
            //nao existe cliente sendo atendido no momento atual,
            //de modo que a simulacao pode avancar no tempo para
            //a chegada do proximo cliente
            if (saidaAtendimento == 0.0) {
                tempo = chegadaCliente;
            } else {
                tempo = controller.minimo(chegadaCliente, saidaAtendimento);
            }

            if (tempo == chegadaCliente) {
                //printf("Chegada de cliente: %lF\n", chegada_cliente);
                //evento de chegada de cliente
                fila++;
                //printf("fila: %lF\n", fila);
                //indica que o caixa esta ocioso
                //logo, pode-se comecar a atender
                //o cliente que acaba de chegar
                if (saidaAtendimento == 0.0) {
                    saidaAtendimento = tempo;
                }
                //gerar o tempo de chegada do proximo cliente
                chegadaCliente = tempo + (-1.0 / tempoMedioClientes) * log(controller.aleatorio());

//                if (saidaAtendimento < chegadaCliente) {
//                    ocupacao += saidaAtendimento - tempo;
//                } else {
//                    ocupacao += chegadaCliente - tempo;
//                }

                //calculo do E[N]
                eN.somaAreas += eN.numeroEventos * (tempo - eN.tempoAnterior);
                eN.tempoAnterior = tempo;
                eN.numeroEventos++;

                //calculo do E[W]
                eWEntrada.somaAreas += eWEntrada.numeroEventos * (tempo - eWEntrada.tempoAnterior);
                eWEntrada.tempoAnterior = tempo;
                eWEntrada.numeroEventos++;
            } else {
                //evento executado se houver saida de cliente
                //ou ainda se houver chegada de cliente, mas
                //o caixa estiver ocioso.
                //a cabeca da fila nao consiste no cliente em atendimento.
                //o cliente que comeca a ser atendido portanto, sai da fila,
                //e passa a estar ainda no comercio, mas em atendimento no caixa.

                //verifica se ha cliente na fila
                if (fila > 0.0) {
                    fila--;
                    double tempoAtendimento = (-1.0 / tempoMedioAtendimento) * log(controller.aleatorio());
                    saidaAtendimento = tempo + tempoMedioAtendimento;
                    somaAtendimentos += tempoAtendimento;

//                    if (saidaAtendimento < chegadaCliente) {
//                        ocupacao += saidaAtendimento - tempo;
//                    } else {
//                        ocupacao += chegadaCliente - tempo;
//                    }

                } else {
                    saidaAtendimento = 0.0;
                }

                if (eN.tempoAnterior < tempo) {
                    //calculo do E[N]
                    eN.somaAreas += eN.numeroEventos * (tempo - eN.tempoAnterior);
                    eN.tempoAnterior = tempo;
                    eN.numeroEventos--;

                    //calculo do E[W]
                    eWSaida.somaAreas += eWSaida.numeroEventos * (tempo - eWSaida.tempoAnterior);
                    eWSaida.tempoAnterior = tempo;
                    eWSaida.numeroEventos++;
                }
            }
        }
        if (saidaAtendimento > tempo) {
            somaAtendimentos -= (saidaAtendimento - tempo);
        }
        //fazendo o calculo da ultima area dos graficos antes do termino da simulacao
        eWSaida.somaAreas += eWSaida.numeroEventos * (tempo - eWSaida.tempoAnterior);
        eWEntrada.somaAreas += eWEntrada.numeroEventos * (tempo - eWEntrada.tempoAnterior);

        double eNFinal = eN.somaAreas / tempo;
        double eW = (eWEntrada.somaAreas - eWSaida.somaAreas) / (double) eWEntrada.numeroEventos;
        double lambda = eWEntrada.numeroEventos / tempo;

        System.out.printf("Ocupacao: %.5f\n", somaAtendimentos / tempo);
        System.out.printf("E[N]: %.15f\n", eNFinal);
        System.out.printf("E[W]: %.10f\n", eW);
        //Little --> en = lambda * ew
        //Little --> en - lambda * ew ~ 0.0
        System.out.printf("Validação little λ: %.15f\n", (eNFinal - lambda * eW));
    }
}
