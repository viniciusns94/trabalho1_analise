/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Vinicius_2
 */
public class Info {
    public Double numeroEventos;
    public Double somaAreas;
    public Double tempoAnterior;
    
    public Info(Double numeroEventos, Double somaAreas, Double tempoAnterior){
        this.numeroEventos = numeroEventos;
        this.somaAreas = somaAreas;
        this.tempoAnterior = tempoAnterior;
    }
}
