package ohtu.kivipaperisakset.vastus;

import ohtu.kivipaperisakset.tekoaly.Tekoaly;

public class KPSvsTekoaly extends KPSVastustaja {

    private Tekoaly tekoaly;

    public KPSvsTekoaly() {
        tekoaly = new Tekoaly();
    }

    @Override
    protected boolean kierros() {
        String ekanSiirto = pelaajanSiirto();
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        if (!onkoOkSiirto(ekanSiirto) || !onkoOkSiirto(tokanSiirto)) {
            return true;
        }
        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        System.out.println(tuomari + "\n");
        return false;
    }
}
