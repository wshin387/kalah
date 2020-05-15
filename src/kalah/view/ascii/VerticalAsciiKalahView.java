package kalah.view.ascii;

import com.qualitascorpus.testsupport.IO;
import kalah.controller.GameController;
import kalah.model.pit.House;
import kalah.model.pit.Store;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VerticalAsciiKalahView extends AsciiKalahView {
    private VerticalAsciiUtil asciiUtil;

    public VerticalAsciiKalahView(IO io, GameController gameController) {
        super(io, gameController);
        this.asciiUtil = new VerticalAsciiUtil(gameController.getTotalSeedCount(), gameController.getHouseCount());
    }

    @Override
    protected void printHorizontalBorder() {
        io.println(asciiUtil.horizontalBorder(gameController.getPlayerCount()));
    }

    @Override
    protected void printPlayers() {
        printEvenPlayerStores();
        printDivider();
        printPitsForPlayers();
        printDivider();
        printOddPlayerStores();
    }

    @Override
    protected void printDivider() {
        io.println(asciiUtil.divider(gameController.getPlayerCount()));
    }

    private void printEvenPlayerStores() {
        Map<Integer, Store> playerStoreMap = new TreeMap<>();
        int playerCount = gameController.getPlayerCount();

        for (int i=2; i<=playerCount; i+=2) {
            Store store = gameController.getStoreForPlayer(i);
            playerStoreMap.put(i, store);
        }

        io.println(asciiUtil.evenPlayerStores(playerStoreMap, playerCount));
    }

    private void printPitsForPlayers() {
        Map<Integer, List<House>> houseMap = new TreeMap<>();
        int playerCount = gameController.getPlayerCount();
        for (int i=1; i<=playerCount; i++) {
            houseMap.put(i, gameController.getHousesForPlayer(i));
        }

        int houseCount = gameController.getHouseCount();
        for (int i=0; i<houseCount; i++) {
            io.println(asciiUtil.rowOfPits(playerCount, i, houseCount, houseMap));
        }
    }

    private void printOddPlayerStores() {
        Map<Integer, Store> playerStoreMap = new TreeMap<>();
        int playerCount = gameController.getPlayerCount();

        for (int i=1; i<=playerCount; i+=2) {
            Store store = gameController.getStoreForPlayer(i);
            playerStoreMap.put(i, store);
        }

        io.println(asciiUtil.oddPlayerStores(playerStoreMap, playerCount));
    }
}
