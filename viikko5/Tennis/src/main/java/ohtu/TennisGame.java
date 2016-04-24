package ohtu;

public class TennisGame {

    private final int ENDTHRESHOLD = 4;
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            m_score1++;
        } else {
            m_score2++;
        }
    }

    public String getScore() {
        String score;
        if (m_score1 < ENDTHRESHOLD && m_score2 < ENDTHRESHOLD) {
            score = midGamePoints();
        } else {
            score = endGamePoints();
        }
        return score;
    }

    private String endGamePoints() {
        String score;
        int pointDifference = Math.abs(m_score1 - m_score2);
        if (m_score1 > m_score2) {
            score = endGamePointsToString(pointDifference, player1Name);
        } else {
            score = endGamePointsToString(pointDifference, player2Name);
        }
        return score;
    }

    private String midGamePoints() {
        String score = "";
        score += midGamePointsToString(m_score1) + "-";
        if (m_score1 == m_score2) {
            score += "All";
        } else {
            score += midGamePointsToString(m_score2);
        }
        return score;
    }

    private String midGamePointsToString(int points) {
        String score = "";
        switch (points) {
            case 0:
                score += "Love";
                break;
            case 1:
                score += "Fifteen";
                break;
            case 2:
                score += "Thirty";
                break;
            case 3:
                score += "Forty";
                break;
        }
        return score;
    }

    private String endGamePointsToString(int pointDifference, String playerName) {
        String score = "Deuce";
        if (pointDifference == 1) {
            score = "Advantage " + playerName;
        } else if (pointDifference >= 2) {
            score = "Win for " + playerName;
        }
        return score;
    }
}
