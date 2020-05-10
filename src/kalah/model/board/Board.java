package kalah.model.board;

import kalah.model.house.House;
import kalah.model.house.Pit;
import kalah.model.house.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board {

    private static final int MINIMUM_HOUSE_COUNT = 1;
    private static final int MINIMUM_INITIAL_SEED_COUNT = 1;
    private static final int MINIMUM_PLAYER_COUNT = 2;

    protected int playerCount;
    protected int houseCount;
    protected int initialSeedCount;
    protected Map<Integer, List<House>> houseMap;
    protected Map<Integer, Store> storeMap;
    
    public Board(int playerCount, int initialSeedCount, int houseCount) {
        if (houseCount < MINIMUM_HOUSE_COUNT || initialSeedCount < MINIMUM_INITIAL_SEED_COUNT || playerCount < MINIMUM_PLAYER_COUNT) {
            throw new RuntimeException(String.format((
                    "Need at least (%d) houses, (%d) seeds and (%d) players. Got (%d) houses, (%d) seeds, and (%d) players."),
                    MINIMUM_HOUSE_COUNT, MINIMUM_INITIAL_SEED_COUNT, MINIMUM_PLAYER_COUNT, houseCount, initialSeedCount, playerCount));
        }

        this.playerCount = playerCount;
        this.initialSeedCount = initialSeedCount;
        this.houseCount = houseCount;

        this.houseMap = new HashMap<>();
        this.storeMap = new HashMap<>();
        
        for (int i=1; i<=playerCount; i++) {
            List<House> houseList = new ArrayList<>();

            for (int j=1; j<=houseCount; j++) {
                houseList.add(new House(i, initialSeedCount, j));
            }

            this.houseMap.put(i, houseList);
            this.storeMap.put(i, new Store(i, 0));
        }

        this.initializeBoard();
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getInitialSeedCount() {
        return initialSeedCount;
    }

    public List<House> getHousesForPlayer(int playerNumber) {
        List<House> houseList = new ArrayList<>();

        for (int i=1; i<=houseCount; i++) {
            houseList.add(getHouseForPlayer(playerNumber, i));
        }

        return houseList;
    }

    public Pit sow(int houseNumber, int playerNumber) {
        Pit pit = this.getHouseForPlayer(playerNumber, houseNumber);
        int numSeeds = pit.removeSeeds();

        while (numSeeds > 0) {
            pit = pit.getNextPit();
            numSeeds -= pit.checkAndSowSeeds(playerNumber);
        }

        this.getStoreForPlayer(playerNumber).checkAndSowSeeds(pit.checkAndCapture(playerNumber), playerNumber);
        return pit;
    }

    House getHouseForPlayer(int playerNumber, int houseNumber) {
        if (!houseMap.containsKey(playerNumber)) {
            throw new RuntimeException(String.format("Player (%d) does not have house (%d).", playerNumber, houseNumber));
        }

        for (House house: this.houseMap.get(playerNumber)) {
            if (house.getHouseNumber() == houseNumber) {
                return house;
            }
        }

        throw new RuntimeException(String.format("Player (%d) does not have house #(%d).", playerNumber, houseNumber));
    }

    public Store getStoreForPlayer(int playerNumber) {
        if (!storeMap.containsKey(playerNumber)) {
            throw new RuntimeException(String.format("Player (%d) does not have a store.", playerNumber));
        }

        return storeMap.get(playerNumber);
    }

    public int nextPlayer(int playerNumber) {
        return playerNumber % this.playerCount + 1;
    }

    public boolean isHouseEmpty(int houseNumber, int playerNumber) {
        return getHouseForPlayer(playerNumber, houseNumber).isEmpty();
    }

    public boolean arePlayerHousesEmpty(int playerNumber) {
        for (House h : getHousesForPlayer(playerNumber)) {
            if (!h.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    abstract void initializeBoard();
}