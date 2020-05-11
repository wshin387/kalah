package kalah.model.board;

public class CircularBoard extends Board {

    public CircularBoard(int playerCount, int initialSeedCount, int houseCount) {
        super(playerCount, initialSeedCount, houseCount);
    }

    @Override
    public void initializeBoard() {
        for (int i=1; i<=playerCount; i++) {
            for (int j=1; j<houseCount; j++) {
                getHouseForPlayer(i, j).setNextPitIfNull(getHouseForPlayer(i, j + 1));
                getHouseForPlayer(i, j).setOppositePitIfNull(getHouseForPlayer(nextPlayer(i), houseCount - j + 1));
            }

            getHouseForPlayer(i, houseCount).setNextPitIfNull(getStoreForPlayer(i));
            getHouseForPlayer(i, houseCount).setOppositePitIfNull(getHouseForPlayer(nextPlayer(i), 1));
            getStoreForPlayer(i).setNextPitIfNull(getHouseForPlayer(nextPlayer(i), 1));
            getStoreForPlayer(i).setOppositePitIfNull(getStoreForPlayer(nextPlayer(i)));
        }
    }
}
