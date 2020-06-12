package kalah.model.bmf;

import kalah.enums.BMFMoveReason;

public class BMFMove {
    int houseNumber;
    BMFMoveReason reason;

    public BMFMove(int houseNumber, BMFMoveReason reason) {
        this.houseNumber = houseNumber;
        this.reason = reason;
    }

    public int getHouseNumber() {
        return this.houseNumber;
    }

    public BMFMoveReason getReason() {
        return this.reason;
    }
}
