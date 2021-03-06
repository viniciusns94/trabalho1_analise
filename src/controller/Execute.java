/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.log;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Info;

/**
 *
 * @author Vinicius_2
 */
public class Execute {

    private double tempo, fila, chegadaCliente, saidaAtendimento, somaAtendimentos;
    private final Controller controller;
    private final Info eN, eWEntrada, eWSaida;
    private FileWriter arqEn, arqWn;
    private PrintWriter gravarArqEn, gravarArqWn;

    public Execute() {
        this.controller = new Controller();
        this.saidaAtendimento = 0.0;
        this.fila = 0.0;
        this.somaAtendimentos = 0.0;
        this.eN = new Info(0.0, 0.0, 0.0);
        this.eWEntrada = new Info(0.0, 0.0, 0.0);
        this.eWSaida = new Info(0.0, 0.0, 0.0);
    }

    public void simulacao(double tempoMedioClientes, double tempoMedioAtendimento, double tempoSimulacao, double intervaloDeTempoConstante) {
        tempoMedioClientes = 1.0 / tempoMedioClientes;
        tempoMedioAtendimento = 1.0 / tempoMedioAtendimento;
        chegadaCliente = (-1.0 / tempoMedioClientes) * log(controller.aleatorio());
        double intervaloDeTempoVariavel = intervaloDeTempoConstante;

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
                //evento de chegada de cliente
                fila++;

                //indica que o caixa esta ocioso
                //logo, pode-se comecar a atender
                //o cliente que acaba de chegar
                if (saidaAtendimento == 0.0) {
                    saidaAtendimento = tempo;
                }
                //gerar o tempo de chegada do proximo cliente
                chegadaCliente = tempo + (-1.0 / tempoMedioClientes) * log(controller.aleatorio());

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
                    saidaAtendimento = tempo + tempoAtendimento;
                    somaAtendimentos += tempoAtendimento;
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
            try {
                if (arqEn == null && arqWn == null) {
                    arqEn = new FileWriter("d:\\E[N].txt");
                    arqWn = new FileWriter("d:\\W[N].txt");
                    gravarArqEn = new PrintWriter(arqEn);
                    gravarArqWn = new PrintWriter(arqWn);
                    gravarArqEn.printf("TEMPO\t\t\t\t\tE[N]\n");
                    gravarArqWn.printf("TEMPO\t\t\t\t\tW[N]\n");
                } else {
                    if (tempo >= intervaloDeTempoVariavel) {
                        intervaloDeTempoVariavel += intervaloDeTempoConstante;
                        if (tempo < 1000) {
                            gravarArqEn.printf("00%.15f\t%.15f\n", tempo, eN.somaAreas / tempo);
                            gravarArqWn.printf("00%.15f\t%.10f\n", tempo, (eWEntrada.somaAreas - eWSaida.somaAreas) / (double) eWEntrada.numeroEventos);
                        } else if (tempo < 1000000) {
                            gravarArqEn.printf("0%.15f\t%.15f\n", tempo, eN.somaAreas / tempo);
                            gravarArqWn.printf("0%.15f\t%.10f\n", tempo, (eWEntrada.somaAreas - eWSaida.somaAreas) / (double) eWEntrada.numeroEventos);
                        } else {
                            gravarArqEn.printf("%.15f\t%.15f\n", tempo, eN.somaAreas / tempo);
                            gravarArqWn.printf("%.15f\t%.10f\n", tempo, (eWEntrada.somaAreas - eWSaida.somaAreas) / (double) eWEntrada.numeroEventos);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Execute.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            arqEn.close();
            arqWn.close();
        } catch (IOException ex) {
            Logger.getLogger(Execute.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.printf("I) Tempo médio de atendimento: %.2f sg.\n", tempoMedioAtendimento);
        System.out.printf("II)\tOcupacao: %.5f\n", somaAtendimentos / tempo);
        System.out.printf("\tλ: %.5f\n", lambda);
        System.out.printf("\tE[N]: %.15f\n", eNFinal);
        System.out.printf("\tE[W]: %.10f\n", eW);
        System.out.printf("III)Validação little: %.15f\n", (eNFinal - lambda * eW));
    }
}
