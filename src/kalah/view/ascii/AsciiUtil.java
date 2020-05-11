package kalah.view.ascii;

import kalah.model.pit.House;
import kalah.model.pit.Store;
import kalah.model.score.Score;

import java.util.Collections;
import java.util.List;

public class AsciiUtil {
    private static final int EXTRA_HOUSE_CHARACTERS = 4;
    private static final int EXTRA_STORE_CHARACTERS = 2;
    private static final char PLAYER_CHAR = 'P';
    private static final char PIPE_CHAR = '|';
    private static final char PLUS_CHAR = '+';
    private static final char DASH_CHAR = '-';
    private static final char OPEN_BRACE_CHAR = '[';
    private static final char CLOSE_BRACE_CHAR = ']';
    private static final char WHITESPACE_CHAR = ' ';

    private int seedLength;
    private int houseLength;

    public AsciiUtil(int seedCount, int houseCount) {
        this.seedLength = numLength(seedCount);
        this.houseLength = numLength(houseCount);
    }

    public String promptMove(int currentPlayerNumber) {
        StringBuilder sb = new StringBuilder();

        sb.append("Player ")
                .append(PLAYER_CHAR)
                .append(currentPlayerNumber)
                .append("'s turn - Specify house number or 'q' to quit: ");

        return sb.toString();
    }

    public String formatScore(Score score) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tplayer ")
                .append(score.getPlayerNumber())
                .append(":")
                .append(score.getScore());

        return sb.toString();
    }

    public String formatWinners(List<Score> winnersList) {
        if (winnersList.size() == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Player ")
                    .append(winnersList.get(0).getPlayerNumber())
                    .append( " wins!");

            return sb.toString();
        }

        return "A tie!";
    }

    public String gameOver() {
        return "Game over";
    }

    public String emptyHouse() {
        return "House is empty. Move again.";
    }

    public String pitsForPlayer(int playerNumber, List<House> houseList, Store store) {
        if (playerNumber % 2 == 1) {
            return formatPits(playerNumber, houseList, store);
        }

        return formatPitsInReverseOrder(playerNumber, houseList, store);
    }

    private String formatPitsInReverseOrder(int playerNumber, List<House> houseList, Store store) {
        Collections.reverse(houseList);
        StringBuilder sb = new StringBuilder();

        sb.append(formatPlayerNumber((playerNumber)))
                .append(formatHouseList(houseList))
                .append(formatStore(store));

        Collections.reverse(houseList);
        return sb.toString();
    }

    private String formatPits(int playerNumber, List<House> houseList, Store store) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatStore(store))
                .append(formatHouseList(houseList))
                .append(formatPlayerNumber(playerNumber));

        return sb.toString();
    }

    public String divider(int numHouses) {
        StringBuilder sb = new StringBuilder();
        sb.append(outerDivider())
                .append(centralDivider(numHouses))
                .append(outerDivider());

        return sb.toString();
    }

    public String horizontalBorder(int numHouses) {
        StringBuilder sb = new StringBuilder();
        sb.append(outerHorizontalBorder())
                .append(centralDivider(numHouses))
                .append(outerHorizontalBorder());

        return sb.toString();
    }

    private String outerHorizontalBorder() {
        StringBuilder sb = new StringBuilder();
        sb.append(PLUS_CHAR)
                .append(repeatChar(DASH_CHAR, seedLength + EXTRA_STORE_CHARACTERS))
                .append(PLUS_CHAR);

        return sb.toString();
    }

    private String centralDivider(int houseCount) {
        StringBuilder sb = new StringBuilder();
        int repeatCount = houseLength + seedLength + EXTRA_HOUSE_CHARACTERS;
        String singleDivider = singleDivider(repeatCount);

        sb.append(repeatString(singleDivider, houseCount))
                .deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private String singleDivider(int repeatCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(repeatChar(DASH_CHAR, repeatCount))
                .append(PLUS_CHAR);

        return sb.toString();
    }

    private String outerDivider() {
        StringBuilder sb = new StringBuilder();

        sb.append(PIPE_CHAR)
                .append(repeatChar(WHITESPACE_CHAR, seedLength + EXTRA_STORE_CHARACTERS))
                .append(PIPE_CHAR);

        return sb.toString();
    }

    private String formatPlayerNumber(int playerNumber) {
        StringBuilder sb = new StringBuilder();

        sb.append(PIPE_CHAR)
                .append(repeatChar(WHITESPACE_CHAR, seedLength - 1))
                .append(PLAYER_CHAR)
                .append(playerNumber)
                .append(WHITESPACE_CHAR)
                .append(PIPE_CHAR);

        return sb.toString();
    }

    private String formatHouseList(List<House> houseList) {
        StringBuilder sb = new StringBuilder();

        for (House house : houseList) {
            sb.append(formatHouse(house));
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private String formatHouse(House house) {
        StringBuilder sb = new StringBuilder();
        sb.append(WHITESPACE_CHAR)
                .append(rightAlignNumber(house.getHouseNumber(), houseLength))
                .append(OPEN_BRACE_CHAR)
                .append(rightAlignNumber(house.getSeedCount(), seedLength))
                .append(CLOSE_BRACE_CHAR)
                .append(WHITESPACE_CHAR)
                .append(PIPE_CHAR);

        return sb.toString();
    }

    private String formatStore(Store store) {
        StringBuilder sb = new StringBuilder();
        sb.append(PIPE_CHAR)
                .append(WHITESPACE_CHAR)
                .append(rightAlignNumber(store.getSeedCount(), seedLength))
                .append(WHITESPACE_CHAR)
                .append(PIPE_CHAR);

        return sb.toString();
    }

    private int numLength(int value) {
        if (value == 0) {
            return 1;
        }

        return (int) Math.log10(Math.abs(value)) + 1;
    }

    private String repeatString(String s, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<times; i++) {
            sb.append(s);
        }

        return sb.toString();
    }

    private String repeatChar(char c, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<times; i++) {
            sb.append(c);
        }

        return sb.toString();
    }

    private String rightAlignNumber(int num, int totalLength) {
        int whiteSpaceLength = totalLength - numLength(num);
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<whiteSpaceLength; i++) {
            sb.append(" ");
        }
        sb.append(num);
        
        return sb.toString();
    }
}
