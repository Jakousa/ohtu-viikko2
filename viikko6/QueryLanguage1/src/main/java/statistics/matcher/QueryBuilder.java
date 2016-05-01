
package statistics.matcher;

import statistics.Player;

public class QueryBuilder {
    Matcher matcher;
    
    public QueryBuilder() {
        matcher = new And();
    }
    
    public Matcher build() {
        return matcher;
    }
    
    public QueryBuilder oneOf(Matcher matcher, Matcher matcher2){
        this.matcher = new Or(matcher, matcher2);
        return this;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(new PlaysIn(team), matcher);
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(new HasFewerThan(value, category), matcher);
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(new HasAtLeast(value, category), matcher);
        return this;
    }
}
