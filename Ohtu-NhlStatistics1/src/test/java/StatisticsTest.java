/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hatchy
 */
public class StatisticsTest {

    Statistics stats;
    List<Player> players;
    Player pl = new Player("Semenko", "EDM", 4, 12);
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(pl);
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
     
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
        players = readerStub.getPlayers();
    }
    
    @Test
    public void konstruktori() {
        stats = new Statistics(readerStub);
        assertEquals(stats.getClass(),Statistics.class);
    }
    
    @Test
    public void konstruktori2(){
        players = readerStub.getPlayers();
        assertEquals(pl, players.get(0));
    }
    
    @Test
    public void hakuOnnistuu() {
        String nimi = "Gretzky";
        Player pelaaja = stats.search(nimi);
        assertTrue(pelaaja.getName().equalsIgnoreCase(nimi));
    }
    
    @Test
    public void pelaajaEiLoydy() {
        String nimi = "GrUtzkI";
        Player pelaaja = stats.search(nimi);
        assertTrue(pelaaja == null);
    }
    
    @Test
    public void joukkue() {
    String nimi = "PIT";
    List<Player> tiimi = stats.team(nimi);
    assertTrue(tiimi.size() == 1);
    
    nimi = "EDM";
    tiimi = stats.team(nimi);
    assertTrue(tiimi.size() == 3);
    
    tiimi = stats.team("Error");
    assertTrue(tiimi.isEmpty());
    }
    
    @Test
    public void karki() {
        List<Player> parhaat = stats.topScorers(1);
        assertTrue(parhaat.get(0).getName().equalsIgnoreCase("Gretzky"));
    }
    
}
