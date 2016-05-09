package ohtu.kivipaperisakset.vastus;

import ohtu.kivipaperisakset.tekoaly.TekoalyParannettu;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSvsParempiTekoaly extends KPSVastustaja {

    private TekoalyParannettu tekoaly;

    public KPSvsParempiTekoaly() {
        tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected boolean kierros() {
        String ekanSiirto = pelaajanSiirto();
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        if (!onkoOkSiirto(ekanSiirto) || !onkoOkSiirto(tokanSiirto)) {
            return true;
        }
        tekoaly.asetaSiirto(ekanSiirto);
        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        System.out.println(tuomari + "\n");
        return false;
    }

}
