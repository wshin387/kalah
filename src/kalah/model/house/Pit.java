package kalah.model.house;

public abstract class Pit {
    private static final int DEFAULT_SOW_AMOUNT = 1;

    protected int playerNumber;
    protected int seedCount;
    protected Pit oppositePit;
    protected Pit nextPit;

    public Pit(int playerNumber, int seedCount) {
        this.playerNumber = playerNumber;
        this.seedCount = seedCount;
    }

    public void setPrevPitIfNull(Pit pit) {
        if (oppositePit != null) {
            return;
        }

        this.oppositePit = pit;
    }

    public void setNextPitIfNull(Pit pit) {
        if (nextPit != null) {
            return;
        }

        this.nextPit = pit;
    }

    public Pit getNextPit() {
        return nextPit;
    }

    public int getSeedCount() {
        return seedCount;
    }

    public boolean isEmpty() {
        return seedCount == 0;
    }

    public int checkAndSowSeeds(int amount, int playerNumber) {
        if (!canSow(playerNumber)) {
            return 0;
        }

        this.seedCount += amount;
        return amount;
    }

    public int checkAndSowSeeds(int playerNumber) {
        return this.checkAndSowSeeds(DEFAULT_SOW_AMOUNT, playerNumber);
    }

    public int checkAndCapture(int playerNumber) {
        if (!canCapture(playerNumber)) {
            return 0;
        }

        return this.removeSeeds() + oppositePit.removeSeeds();
    }

    public int removeSeeds() {
        int result = this.seedCount;
        this.seedCount = 0;
        return result;
    }

    public abstract boolean movesAgain(int playerNumber);
    public abstract boolean canSow(int playerNumber);
    public abstract boolean canCapture(int playerNumber);
}
