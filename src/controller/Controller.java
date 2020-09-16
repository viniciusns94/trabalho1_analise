/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Random;

/**
 *
 * @author Vinicius_2
 */
public class Controller {

    private final Random gerador;

    public Controller() {
//        this.gerador = new Random();
        int semente = 1556915527;
        System.out.println("Semente: " + semente);
        this.gerador = new Random(semente);
    }

    public Double aleatorio() {
        return gerador.nextDouble();
    }

    public Double minimo(Double num1, Double num2) {
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }
}
