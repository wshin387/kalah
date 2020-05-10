package kalah.controller;

import kalah.model.board.Board;
import kalah.model.house.House;
import kalah.model.house.Store;
import kalah.model.score.Score;

import java.util.*;

public class ScoreController {

    private Board board;

    public ScoreController(Board board) {
        this.board = board;
    }

    public Score getScoreForPlayer(int playerNumber) {
        int seedCount = 0;

        for (House h : board.getHousesForPlayer(playerNumber)) {
            seedCount += h.getSeedCount();
        }

        Store store = board.getStoreForPlayer(playerNumber);
        seedCount += store.getSeedCount();

        return new Score(playerNumber, seedCount);
    }

    public List<Score> getWinners() {
        List<Score> result = new ArrayList<>();
        Queue<Score> queue = new PriorityQueue<>(this.board.getPlayerCount(), new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        for (int i=1; i<=board.getPlayerCount(); i++) {
            queue.add(getScoreForPlayer(i));
        }

        int maxScore = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            Score score = queue.poll();
            if (score.getScore() >= maxScore) {
                result.add(score);
                maxScore = score.getScore();
            }
        }

        return result;
    }
}
