package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukonLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Koon ja kapasiteetin oltava >= 0");
        }
        this.joukonLuvut = jononAlustaja(kapasiteetti);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private int[] jononAlustaja(int kapasiteetti) {
        int[] jono = new int[kapasiteetti];
        for (int i = 0; i < jono.length; i++) {
            jono[i] = 0;
        }
        return jono;
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        joukonLuvut[alkioidenLkm++] = luku;
        if (alkioidenLkm == joukonLuvut.length) {
            kasvataTaulukkoa();
        }
        return true;
    }

    private void kasvataTaulukkoa() {
        int[] taulukkoOld = joukonLuvut;
        kopioiTaulukko(joukonLuvut, taulukkoOld);
        joukonLuvut = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, joukonLuvut);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                tiivistaTaulukko(i);
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void tiivistaTaulukko(int kohta) {
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            joukonLuvut[j] = joukonLuvut[j + 1];
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos += joukonLuvut[i];
            if (i < alkioidenLkm - 1) {
                tuotos += ", ";
            }
        }
        return "{" + tuotos + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(joukonLuvut, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        for (int i : a.toIntArray()) {
            yhdiste.lisaa(i);
        }
        for (int i : b.toIntArray()) {
            yhdiste.lisaa(i);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        for (int i : a.toIntArray()) {
            for (int j : b.toIntArray()) {
                lisaaJosSama(i, j, leikkaus);
            }
        }
        return leikkaus;

    }

    private static void lisaaJosSama(int luku1, int luku2, IntJoukko joukko) {
        if (luku1 == luku2) {
            joukko.lisaa(luku1);
        }
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        for (int i : a.toIntArray()) {
            erotus.lisaa(i);
        }
        for (int i : b.toIntArray()) {
            erotus.poista(i);
        }
        return erotus;
    }

}
