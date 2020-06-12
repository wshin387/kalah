package kalah.view.ascii;

import kalah.model.bmf.BMFMove;

public class BMFAsciiUtil extends AsciiUtil {
    int bmfPlayerNumber;

    public BMFAsciiUtil(int seedCount, int houseCount, int bmfPlayerNumber) {
        super(seedCount, houseCount);
        this.bmfPlayerNumber = bmfPlayerNumber;
    }

    public String formatBMFMove(BMFMove bmfMove) {
        StringBuilder sb = new StringBuilder();

        sb.append("Player P")
                .append(bmfPlayerNumber)
                .append(" (Robot) chooses house #")
                .append(bmfMove.getHouseNumber())
                .append(" because ")
                .append(bmfMove.getReason().toString());

        return sb.toString();
    }
}
