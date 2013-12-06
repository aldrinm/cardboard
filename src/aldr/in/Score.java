package aldr.in;

/**
 * Created with IntelliJ IDEA.
 * User: aldrin
 * Date: 10/7/13
 * Time: 8:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Score {

    int score;
    String playerName;

    public Score() {
        score = 0;
    }

    public Score(int score) {
        this.score = score;
    }

    public Score(String playerName) {
        this();
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                '}';
    }
}
