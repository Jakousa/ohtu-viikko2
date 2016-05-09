/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.peli;

import java.util.Scanner;
import ohtu.kivipaperisakset.vastus.*;

/**
 *
 * @author hatchy
 */
public class KPSPeli {

    private static final Scanner scanner = new Scanner(System.in);

    public static void aloitaPeli() {

        KPSVastustaja vs;

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                vs = PeliTehdas.vsPelaaja();
            } else if (vastaus.endsWith("b")) {
                vs = PeliTehdas.vsHelppoTekoaly();
            } else if (vastaus.endsWith("c")) {
                vs = PeliTehdas.vsVaikeaTekoaly();
            } else {
                break;
            }
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            vs.pelaa();
        }
    }
}
