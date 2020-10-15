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
        int semente = 10203040;
//        System.out.println("Semente: " + semente);
        this.gerador = new Random(semente);
//        this.gerador = new Random();
    }

    public Double aleatorio() {
        Double n = 1.0 - gerador.nextDouble();
        return n;
    }

    public Double minimo(Double num1, Double num2) {
        if (num1 < num2) {
            return num1;
        }
        return num2;
    }
}
