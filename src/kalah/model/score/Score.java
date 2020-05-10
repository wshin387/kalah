package kalah.model.score;

public class Score {

    private int playerNumber;
    private int score;

    public Score(int playerNumber, int score) {
        this.playerNumber = playerNumber;
        this.score = score;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getScore() {
        return score;
    }
}
