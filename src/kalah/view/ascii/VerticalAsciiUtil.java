package kalah.view.ascii;

import kalah.model.pit.House;
import kalah.model.pit.Store;

import java.util.List;
import java.util.Map;

public class VerticalAsciiUtil extends AsciiUtil {
    private static final int EXTRA_HOUSE_CHARACTERS = 7;

    public VerticalAsciiUtil(int seedCount, int houseCount) {
        super(seedCount, houseCount);
    }

    @Override
    public String horizontalBorder(int playerCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(PLUS_CHAR)
                .append(repeatString(repeatChar(DASH_CHAR, EXTRA_HOUSE_CHARACTERS), playerCount))
                .append(repeatChar(DASH_CHAR, playerCount - 1))
                .append(PLUS_CHAR);

        return sb.toString();
    }

    @Override
    public String divider(int numPlayers) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        temp.append(repeatChar(DASH_CHAR, EXTRA_HOUSE_CHARACTERS))
                .append(PLUS_CHAR);

        sb.append(PLUS_CHAR)
                .append(repeatString(temp.toString(), numPlayers));

        return sb.toString();
    }

    public String evenPlayerStores(Map<Integer, Store> map, int playerCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(PIPE_CHAR);

        for (int i=1; i<=playerCount; i++) {
            if (i % 2 == 1) {
                sb.append(repeatChar(WHITESPACE_CHAR, EXTRA_HOUSE_CHARACTERS))
                        .append(PIPE_CHAR);
                continue;
            }

            sb.append(formatPlayerNumberAndStore(i, map.get(i)));
        }

        return sb.toString();
    }

    public String oddPlayerStores(Map<Integer, Store> map, int playerCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(PIPE_CHAR);

        for (int i=1; i<=playerCount; i++) {
            if (i % 2 == 0) {
                sb.append(repeatChar(WHITESPACE_CHAR, EXTRA_HOUSE_CHARACTERS))
                        .append(PIPE_CHAR);
                continue;
            }

            sb.append(formatPlayerNumberAndStore(i, map.get(i)));
        }

        return sb.toString();
    }

    public String rowOfPits(int playerCount, int houseIndex, int houseCount, Map<Integer, List<House>> houseMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(PIPE_CHAR);

        for (int j=1; j<=playerCount; j++) {
            int housePos = (j%2 == 0) ? houseCount - houseIndex - 1 : houseIndex;

            sb.append(WHITESPACE_CHAR)
                    .append(rightAlignNumber(housePos+1, houseLength))
                    .append(OPEN_BRACE_CHAR)
                    .append(rightAlignNumber(houseMap.get(j).get(housePos).getSeedCount(), seedLength))
                    .append(CLOSE_BRACE_CHAR)
                    .append(WHITESPACE_CHAR)
                    .append(PIPE_CHAR);
        }

        return sb.toString();
    }

    private String formatPlayerNumberAndStore(int playerNumber, Store store) {
        StringBuilder sb = new StringBuilder();
        sb.append(WHITESPACE_CHAR)
                .append(PLAYER_CHAR)
                .append(playerNumber)
                .append(WHITESPACE_CHAR)
                .append(rightAlignNumber(store.getSeedCount(), seedLength))
                .append(WHITESPACE_CHAR)
                .append(PIPE_CHAR);

        return sb.toString();
    }
}
