package kalah.model.pit;

public class Store extends Pit {

    public Store(int playerNumber, int seedCount) {
        super(playerNumber, seedCount);
    }

    @Override
    public boolean movesAgain(int playerNumber) {
        return this.playerNumber == playerNumber;
    }

    @Override
    public boolean canSow(int playerNumber) {
        return this.playerNumber == playerNumber;
    }

    @Override
    public boolean canCapture(int playerNumber) {
        return false;
    }
}
