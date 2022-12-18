/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pricipal;

import clases.CalculadoraManual;

/**
 *
 * @author jcarv
 */
public class Principal {
    
    public static void main(String[] args) {
        
        int numero1 = 2400010;
        int numero2 = 10;
        
        CalculadoraManual.sumarMenu(numero1, numero2);
        System.out.println("Suma: " + numero1 + " + " + numero2);
        
        CalculadoraManual.restarMenu(numero1, numero2);
        System.out.println("Resta: " + numero1 + " - " + numero2);
        
        CalculadoraManual.multiplicarMenu(numero1, numero2);
        System.out.println("Multiplicación: " + numero1 + " * " + numero2);
        
        CalculadoraManual.dividirMenu(numero1, numero2);
        System.out.println("División: " + numero1 + " / " + numero2);
        
        System.out.println(numero1 + "   " + numero2);

    }
    
}
