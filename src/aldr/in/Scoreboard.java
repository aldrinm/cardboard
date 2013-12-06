package aldr.in;

/**
 * Created with IntelliJ IDEA.
 * User: aldrin
 * Date: 10/6/13
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Scoreboard {
    Score score1;
    Score score2;

    public Scoreboard() {
        this.score1 = new Score();
        this.score2 = new Score();
    }

    public Scoreboard(Score score1, Score score2) {
        this.score1 = score1;
        this.score2 = score2;
    }

    public Score getScore1() {
        return score1;
    }

    public void setScore1(Score score1) {
        this.score1 = score1;
    }

    public Score getScore2() {
        return score2;
    }

    public void setScore2(Score score2) {
        this.score2 = score2;
    }

    @Override
    public String toString() {
        return "Scoreboard{" +
                "score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }
}

