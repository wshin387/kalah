package kalah.view.ascii;

import com.qualitascorpus.testsupport.IO;
import kalah.controller.BMFGameController;
import kalah.model.bmf.BMFMove;

public class BMFAsciiKalahView extends AsciiKalahView {
    private BMFGameController gameController;
    private BMFAsciiUtil asciiUtil;

    public BMFAsciiKalahView(IO io, BMFGameController gameController) {
        super(io, gameController);
        this.gameController = gameController;
        this.asciiUtil = new BMFAsciiUtil(gameController.getTotalSeedCount(), gameController.getHouseCount());
    }

    @Override
    public String promptMove() {
        if (gameController.getCurrentPlayerNumber() == 1) {
            return io.readFromKeyboard(asciiUtil.promptMove(gameController.getCurrentPlayerNumber()));
        }

        BMFMove bmfMove = gameController.getBMFMove();
        io.println(asciiUtil.formatBMFMove(bmfMove));
        return String.valueOf(bmfMove.getHouseNumber());
    }
}
