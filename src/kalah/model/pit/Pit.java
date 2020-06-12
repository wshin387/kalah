package kalah.model.pit;

import java.util.HashMap;
import java.util.Map;

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

    public static Pit copyOf(Pit p) {
        if (p == null) {
            return null;
        }

        Map<Pit, Pit> map = new HashMap<>(); //real -> copy
        Pit current = p;

        while (current != null && !map.containsKey(current)) {
            map.put(current, instantiatePit(current));
            current = current.nextPit;
        }

        for (Map.Entry<Pit, Pit> entry: map.entrySet()) {
            Pit pit = entry.getValue();
            pit.nextPit = map.get(entry.getKey().nextPit);
            pit.oppositePit = map.get(entry.getKey().oppositePit);
        }

        return map.get(p);
    }

    private static Pit instantiatePit(Pit p) {
        Pit result;

        if (p instanceof House) {
            House h = (House) p;
            result = new House(h.playerNumber, h.seedCount, h.getHouseNumber());
        } else {
            Store s = (Store) p;
            result = new Store(s.playerNumber, s.seedCount);
        }

        return result;
    }

    public void setOppositePitIfNull(Pit pit) {
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
