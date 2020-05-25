package kalah.view.ascii;

import com.qualitascorpus.testsupport.IO;
import kalah.controller.BMFGameController;
import kalah.model.bmf.BMFMove;

public class BMFAsciiKalahView extends AsciiKalahView {
    private BMFGameController gameController;
    private BMFAsciiUtil asciiUtil;
    private int bmfPlayerNumber;

    public BMFAsciiKalahView(IO io, BMFGameController gameController) {
        super(io, gameController);
        this.gameController = gameController;
        this.bmfPlayerNumber = BMFGameController.BMF_PLAYER_NUMBER;
        this.asciiUtil = new BMFAsciiUtil(gameController.getTotalSeedCount(), gameController.getHouseCount(), bmfPlayerNumber);
    }

    @Override
    public String promptMove() {
        if (gameController.getCurrentPlayerNumber() != bmfPlayerNumber) {
            return super.promptMove();
        }

        BMFMove bmfMove = gameController.getBMFMove();
        io.println(asciiUtil.formatBMFMove(bmfMove));
        return String.valueOf(bmfMove.getHouseNumber());
    }
}
