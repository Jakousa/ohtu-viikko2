package ohtu.kivipaperisakset.vastus;

public class KPSvsPelaaja extends KPSVastustaja {


    @Override
    protected boolean kierros() {
        String ekanSiirto = pelaajanSiirto();
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
        if (!onkoOkSiirto(ekanSiirto) || !onkoOkSiirto(tokanSiirto)) {
            return true;
        }
        tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
        System.out.println(tuomari + "\n");
        return false;
    }
}
