package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.controller.GameController;
import kalah.enums.MoveResult;
import kalah.model.board.Board;
import kalah.model.board.CircularBoard;
import kalah.view.KalahView;
import kalah.view.ascii.AsciiKalahView;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	private static final String QUIT_COMMAND = "q";

	private GameController gameController;
	private KalahView view;

	public static void main(String... args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		initializeControllerAndView(io);
		playKalah();
	}

	private void initializeControllerAndView(IO io) {
		Board board = new CircularBoard(2, 4, 6);

		this.gameController = new GameController(board);
		this.view = new AsciiKalahView(io, gameController);
	}

	private void playKalah() {
		while (true) {
			view.printBoard();
			String input = view.promptMove();

			if (input.equals(QUIT_COMMAND)) {
				view.printGameOver();
				view.printBoard();
				return;
			}

			MoveResult outcome = gameController.makeMove(Integer.parseInt(input));

			switch (outcome) {
				case EMPTY_HOUSE:
					view.printEmptyHouse();
					break;
				case GAME_OVER:
					view.printBoard();
					view.printGameOver();
					view.printBoard();
					view.printResult();
					return;
			}
		}
	}

}
