/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.komento;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

/**
 *
 * @author hatchy
 */
public class Erotus implements Komento {

    Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;
    int edellinenTulos;

    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta,
            JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        sovellus.miinus(arvo);
        int laskunTulos = sovellus.tulos();

        try {
            edellinenTulos = Integer.parseInt(tuloskentta.getText());
        } catch (Exception e) {
            edellinenTulos = 0;
        }
        asetaRuutu(laskunTulos);
    }

    @Override
    public void peru() {
        asetaRuutu(edellinenTulos);
    }
    
    private void asetaRuutu(int laskunTulos) {
                syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
    }
}
