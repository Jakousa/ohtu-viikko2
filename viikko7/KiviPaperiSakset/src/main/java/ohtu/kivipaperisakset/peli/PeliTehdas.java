/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.peli;

import ohtu.kivipaperisakset.vastus.*;

/**
 *
 * @author hatchy
 */
public class PeliTehdas {
    public static KPSVastustaja vsPelaaja() {
        return new KPSvsPelaaja();
    }
    
    public static KPSVastustaja vsHelppoTekoaly() {
        return new KPSvsTekoaly();
    }
    
    public static KPSVastustaja vsVaikeaTekoaly() {
        return new KPSvsParempiTekoaly();
    }
    
}
