package game.chess.utils;

import java.util.List;

import game.chess.entity.Board;
import game.chess.entity.Piece;
import game.chess.layout.Layout;
import game.chess.layout.LayoutEntry;
import game.chess.factory.PieceFactory;

public class ChessUtils {
	public static void placePieces(Board board, Layout layout, List<Piece> pieces) {
		if (! ChessUtils.layoutFitBoard(layout, board)) {
			throw new IllegalArgumentException("Piece's layout won't fit in that board");
		}
		pieces.clear();

		for (LayoutEntry entry : layout.getEntries()) {
			Piece piece = PieceFactory.from(entry, (float) board.getTileSize());
			board.place(piece, entry.getRow(), entry.getCol());
			pieces.add(piece);
		}
	}

	private static boolean layoutFitBoard(Layout layout, Board board) {
		return layout.getRowCount() <= board.getRowCount() && layout.getColCount() <= board.getColCount();
	}
}