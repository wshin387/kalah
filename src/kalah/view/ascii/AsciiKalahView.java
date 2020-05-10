package kalah.view.ascii;

import com.qualitascorpus.testsupport.IO;
import kalah.controller.GameController;
import kalah.model.house.House;
import kalah.model.house.Store;
import kalah.view.KalahView;

import java.util.List;

public class AsciiKalahView implements KalahView {

    private IO io;
    private GameController gameController;
    private AsciiUtil asciiUtil;

    public AsciiKalahView(IO io, GameController gameController) {
        this.io = io;
        this.gameController = gameController;
        this.asciiUtil = new AsciiUtil(gameController.getTotalSeedCount(), gameController.getHouseCount());
    }

    @Override
    public String promptMove() {
        return io.readFromKeyboard(asciiUtil.promptMove(gameController.getCurrentPlayerNumber()));
    }

    @Override
    public void printGameOver() {
        io.println(asciiUtil.gameOver());
    }

    @Override
    public void printEmptyHouse() {
        io.println(asciiUtil.emptyHouse());
    }

    @Override
    public void printBoard() {
        printHorizontalBorder();
        printPlayers();
        printHorizontalBorder();
    }

    @Override
    public void printResult() {
        for (int i = 1; i <= gameController.getPlayerCount(); i++) {
            io.println(asciiUtil.formatScore(gameController.getScoreForPlayer(i)));
        }

        io.println(asciiUtil.formatWinners(gameController.getWinners()));
    }

    private void printPlayers() {
        for (int i = gameController.getPlayerCount(); i >= 1; i--) {
            printPitsForPlayer(i);

            if (i > 1) {
                printDivider();
            }
        }
    }

    private void printHorizontalBorder() {
        io.println(asciiUtil.horizontalBorder(gameController.getHouseCount()));
    }

    private void printDivider() {
        io.println(asciiUtil.divider(gameController.getHouseCount()));
    }

    private void printPitsForPlayer(int playerNumber) {
        List<House> houseList = gameController.getHousesForPlayer(playerNumber);
        int storePos = getStorePosition(playerNumber);
        Store store = gameController.getStoreForPlayer(storePos);

        io.println(asciiUtil.pitsForPlayer(playerNumber, houseList, store));
    }

    private int getStorePosition(int playerNumber) {
        return playerNumber % gameController.getPlayerCount() + 1;
    }
}
