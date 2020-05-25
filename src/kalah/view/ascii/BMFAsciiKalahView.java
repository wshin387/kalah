package kalah.view.ascii;

import com.qualitascorpus.testsupport.IO;
import kalah.controller.BMFGameController;
import kalah.model.bmf.BMFMove;

public class BMFAsciiKalahView extends AsciiKalahView {
    private BMFGameController gameController;

    public BMFAsciiKalahView(IO io, BMFGameController gameController) {
        super(io, gameController);
        this.gameController = gameController;
    }

    @Override
    public String promptMove() {
        if (gameController.getCurrentPlayerNumber() == 1) {
            return io.readFromKeyboard(asciiUtil.promptMove(gameController.getCurrentPlayerNumber()));
        }

        BMFMove bmfMove = gameController.getBMFMove();

        io.println("Player P2 (Robot) chooses house #" + bmfMove.getHouseNumber() + " because " + bmfMove.getReason().toString());

        return String.valueOf(bmfMove.getHouseNumber());
    }
}
