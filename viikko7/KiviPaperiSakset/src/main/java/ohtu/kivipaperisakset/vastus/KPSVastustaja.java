/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.vastus;

import java.util.Scanner;
import ohtu.kivipaperisakset.peli.Tuomari;

/**
 *
 * @author hatchy
 */
public abstract class KPSVastustaja {

    protected static final Scanner scanner = new Scanner(System.in);
    protected static Tuomari tuomari;
    
    
    public KPSVastustaja(){
        tuomari = new Tuomari();
    }
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    public void pelaa() {
        while (true) {
            if (kierros()) break;
        }
        System.out.println("\nKiitos!");
        System.out.println(tuomari);
    }
    
    protected abstract boolean kierros();

    protected static String pelaajanSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }
}
