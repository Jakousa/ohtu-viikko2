/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import java.util.List;
import ohtu.verkkokauppa.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author hatchy
 */
public class TilisiirtoTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(pankki, varasto, viite);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void kahdenTuotteenOstossaPankinMetodiaTilisiirtoKutsutaanOikein() {
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2,"jäätee", 3));
        
        k.lisaaKoriin(2);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(8));
    }
    
    @Test
    public void kahdenSamanTuotteenOstossaPankinMetodiaTilisiirtoKutsutaanOikein() {
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));
    }
    
    @Test
    public void kahdenTuotteenOstossaToinenLoppuPankinMetodiaTilisiirtoKutsutaanOikein() {
                when(viite.uusi()).thenReturn(42);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2,"jäätee", 3));
        
        k.lisaaKoriin(2);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"maito", 5));
        
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2,"jäätee", 3));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(3));
    }
    
    @Test
    public void uusiViitenumeroJokaiselleMaksutapahtumalle() {        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(viite, times(1)).uusi();
        
                
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(1,"jäätee", 3));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("matti", "54321");
        
        verify(viite, times(2)).uusi();
        
    }
    
    @Test
    public void koristaPoistoPalauttaaOikeanTuotteen() {
        Tuote t = new Tuote(1,"jäätee",3);
        when(varasto.haeTuote(1)).thenReturn(t);
        k.poistaKorista(1);
        
        verify(varasto, times(1)).palautaVarastoon(t);
    }
}
