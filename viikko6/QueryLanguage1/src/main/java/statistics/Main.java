package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        Matcher q = new Or( new HasFewerThan(4, "points"),
                            new HasAtLeast(60, "assists"));
        
        QueryBuilder query = new QueryBuilder();
        
        Matcher n = query.playsIn("PHI").
                    hasAtLeast(10, "goals").
                    hasAtLeast(10, "assists").build();
        
        QueryBuilder q2 = new QueryBuilder();
        QueryBuilder q3 = new QueryBuilder();
        QueryBuilder q4 = new QueryBuilder();
        Matcher p = q2.oneOf(q3.hasFewerThan(4, "points").build(), 
                    q4.hasAtLeast(60, "assists").build()).build();
        
        for (Player player : stats.matches(q)) {
            System.out.println( player );
        }
        System.out.println("raja");
        for (Player player : stats.matches(p)) {
            System.out.println( player );
        }
    }
}
