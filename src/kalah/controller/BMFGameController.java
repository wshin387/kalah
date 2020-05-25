package kalah.controller;

import kalah.enums.BMFMoveReason;
import kalah.model.bmf.BMFMove;
import kalah.model.board.Board;
import kalah.model.pit.Pit;

public class BMFGameController extends GameController {

    public static final int BMF_PLAYER_NUMBER = 2;

    public BMFGameController(Board board) {
        super(board);
    }

    public BMFMove getBMFMove() {
        for (int i=1; i<=board.getHouseCount(); i++) {
            Pit pit = board.getHouseForPlayer(BMF_PLAYER_NUMBER, i);

            if (pit.isEmpty()) {
                continue;
            }

            Pit p = board.fakeSow(i, BMF_PLAYER_NUMBER);

            if (p.movesAgain(BMF_PLAYER_NUMBER)) {
                return new BMFMove(i, BMFMoveReason.ADDITIONAL_MOVE);
            }
        }

        for (int i=1; i<=board.getHouseCount(); i++) {
            Pit pit = board.getHouseForPlayer(BMF_PLAYER_NUMBER, i);

            if (pit.isEmpty()) {
                continue;
            }

            Pit p = board.fakeSow(i, BMF_PLAYER_NUMBER);

            if (p.canCapture(BMF_PLAYER_NUMBER)) {
                return new BMFMove(i, BMFMoveReason.CAPTURE);
            }
        }

        for (int i=1; i<=board.getHouseCount(); i++) {
            Pit pit = board.getHouseForPlayer(BMF_PLAYER_NUMBER, i);

            if (pit.isEmpty()) {
                continue;
            }

            Pit p = board.fakeSow(i, BMF_PLAYER_NUMBER);

            if (p.canSow(BMF_PLAYER_NUMBER)) {
                return new BMFMove(i, BMFMoveReason.LEGAL);
            }
        }


        return new BMFMove(-1, BMFMoveReason.NONE_AVAILABLE);
    }
}
