package kalah.view.ascii;

import kalah.model.bmf.BMFMove;

public class BMFAsciiUtil extends AsciiUtil {
    public BMFAsciiUtil(int seedCount, int houseCount) {
        super(seedCount, houseCount);
    }

    public String formatBMFMove(BMFMove bmfMove) {
        StringBuilder sb = new StringBuilder();

        sb.append("Player P2 (Robot) chooses house #")
                .append(bmfMove.getHouseNumber())
                .append(" because ")
                .append(bmfMove.getReason().toString());

        return sb.toString();
    }
}
