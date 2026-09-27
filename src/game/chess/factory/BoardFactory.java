package game.chess.factory;

import game.chess.Chess;
import game.chess.entity.Board;

public class BoardFactory {

// ======================================================================================================================================================

	public static Board of(Chess.Type type) {
		switch (type) {
			case CLASSIC:
				return BoardFactory.ofClassic();
			case GIANT:
				return BoardFactory.ofGiant();
			case FOUR_PLAYER:
				return BoardFactory.ofFourPlayer();
			default:
				return BoardFactory.ofClassic();
		}
	}

	private static Board ofClassic() {
		Board board = new Board();
		board.setRowCount(8);
		board.setColCount(8);
		board.setMargin(10);
		return board;
	}

	private static Board ofGiant() {
		Board board = new Board();
		board.setRowCount(20);
		board.setColCount(20);
		board.setMargin(10);
		return board;
	}

	private static Board ofFourPlayer() {
		int rowCount = 14;
		int colCount = 14;
		int margin = 10;
		int crease = 3;

		Board board = new Board();
		board.setRowCount(rowCount);
		board.setColCount(colCount);
		board.setMargin(margin);

		// Disable TOP-LEFT
		for (int row = 0; row < crease; row++) {
			for (int col = 0; col < crease; col++) {
				board.disable(row, col);
			}
		}

		// Disable TOP-RIGHT
		for (int row = 0; row < crease; row++) {
			for (int col = colCount - 1; col >= colCount - crease; col--) {
				board.disable(row, col);
			}
		}

		// Disable BOTTOM-LEFT
		for (int row = rowCount - 1; row >= rowCount - crease; row--) {
			for (int col = 0; col < crease; col++) {
				board.disable(row, col);
			}
		}

		// Disable BOTTOM-RIGHT
		for (int row = rowCount - 1; row >= rowCount - crease; row--) {
			for (int col = colCount - 1; col >= colCount - crease; col--) {
				board.disable(row, col);
			}
		}

		return board;
	}
}