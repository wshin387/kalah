package kalah.model.house;

public class House extends Pit {

    private int houseNumber;

    public House(int playerNumber, int seedCount, int houseNumber) {
        super(playerNumber, seedCount);
        this.houseNumber = houseNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    @Override
    public boolean movesAgain(int playerNumber) {
        return false;
    }

    @Override
    public boolean canSow(int playerNumber) {
        return true;
    }

    @Override
    public boolean canCapture(int playerNumber) {
        return this.seedCount == 1 && this.playerNumber == playerNumber && !oppositePit.isEmpty();
    }
}
