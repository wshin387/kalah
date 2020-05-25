package kalah.controller;

import kalah.enums.MoveResult;
import kalah.model.board.Board;
import kalah.model.pit.House;
import kalah.model.pit.Pit;
import kalah.model.pit.Store;
import kalah.model.score.Score;

import java.util.List;

public class GameController {
    
    protected Board board;
    protected ScoreController scoreController;
    protected int currentPlayerNumber;

    public GameController(Board board) {
        this.board = board;
        this.scoreController = new ScoreController(board);
        currentPlayerNumber = 1;
    }

    public MoveResult makeMove(int houseNumber) {
        if (isCurrentPlayerHouseEmpty(houseNumber)) {
            return MoveResult.EMPTY_HOUSE;
        }
        
        Pit pit = board.sow(houseNumber, currentPlayerNumber);
        
        if (!pit.movesAgain(currentPlayerNumber)) {
            currentPlayerNumber = board.nextPlayer(currentPlayerNumber);
        }
        
        if (areCurrentPlayerHousesEmpty()) {
            return MoveResult.GAME_OVER;
        }

        return MoveResult.NEXT_MOVE;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public int getPlayerCount() {
        return board.getPlayerCount();
    }

    public int getHouseCount() {
        return board.getHouseCount();
    }

    public int getTotalSeedCount() {
        return board.getPlayerCount() * board.getInitialSeedCount() * board.getHouseCount();
    }

    public List<House> getHousesForPlayer(int playerNumber) {
        return board.getHousesForPlayer(playerNumber);
    }

    public Store getStoreForPlayer(int playerNumber) {
        return board.getStoreForPlayer(playerNumber);
    }

    public List<Score> getWinners() {
        return scoreController.getWinners();
    }

    public Score getScoreForPlayer(int playerNum) {
        return scoreController.getScoreForPlayer(playerNum);
    }

    private boolean isCurrentPlayerHouseEmpty(int houseNumber) {
        return board.isHouseEmpty(houseNumber, currentPlayerNumber);
    }

    private boolean areCurrentPlayerHousesEmpty() {
        return board.arePlayerHousesEmpty(currentPlayerNumber);
    }
}
