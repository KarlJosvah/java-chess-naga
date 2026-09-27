package game.chess.factory;

import java.util.ArrayList;

import game.chess.Chess;
import game.chess.entity.Board;
import game.chess.entity.Piece;
import game.chess.layout.Layout;
import game.chess.layout.LayoutEntry;

public class ChessFactory {
	public static void placePieces(Board board, Layout layout, ArrayList<Piece> pieces) {
		if (! ChessFactory.layoutFitBoard(layout, board)) {
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