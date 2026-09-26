package game.chess.builder;

import java.util.ArrayList;

import game.chess.Chess;
import game.chess.entity.Piece;
import game.chess.layout.Layout;

public class LayoutBuilder {

// ======================================================================================================================================================

	public static Layout of(Chess.Type type) {
		switch(type) {
			case CLASSIC:
				return LayoutBuilder.ofClassic();
			case FOUR_PLAYER:
				return LayoutBuilder.ofFourPlayer();
			default:
				return LayoutBuilder.ofClassic();
		}
	}

	private static Layout ofClassic() {
		Layout layout = new Layout(Chess.Type.CLASSIC, 8, 8);

		// White main pieces (Row 0)
		layout.addPiece(Piece.Type.ROOK, Piece.Color.WHITE, 0, 0);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.WHITE, 0, 1);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.WHITE, 0, 2);
		layout.addPiece(Piece.Type.QUEEN, Piece.Color.WHITE, 0, 3);
		layout.addPiece(Piece.Type.KING, Piece.Color.WHITE, 0, 4);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.WHITE, 0, 5);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.WHITE, 0, 6);
		layout.addPiece(Piece.Type.ROOK, Piece.Color.WHITE, 0, 7);

		// White pawns (Row 1)
		for (int col = 0; col < 8; col++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.WHITE, 1, col);
		}

		// Black pawns (Row 6)
		for (int col = 0; col < 8; col++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.BLACK, 6, col);
		}

		// Black main pieces (Row 7)
		layout.addPiece(Piece.Type.ROOK, Piece.Color.BLACK, 7, 0);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.BLACK, 7, 1);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.BLACK, 7, 2);
		layout.addPiece(Piece.Type.QUEEN, Piece.Color.BLACK, 7, 3);
		layout.addPiece(Piece.Type.KING, Piece.Color.BLACK, 7, 4);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.BLACK, 7, 5);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.BLACK, 7, 6);
		layout.addPiece(Piece.Type.ROOK, Piece.Color.BLACK, 7, 7);

		return layout;
	}

	private static Layout ofFourPlayer() {
		Layout layout = new Layout(Chess.Type.FOUR_PLAYER, 14, 14);

		// --- South Player (WHITE) ---
		// Back Rank (Row 0)
		layout.addPiece(Piece.Type.ROOK, Piece.Color.WHITE, 0, 3);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.WHITE, 0, 4);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.WHITE, 0, 5);
		layout.addPiece(Piece.Type.KING, Piece.Color.WHITE, 0, 6);
		layout.addPiece(Piece.Type.QUEEN, Piece.Color.WHITE, 0, 7);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.WHITE, 0, 8);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.WHITE, 0, 9);
		layout.addPiece(Piece.Type.ROOK, Piece.Color.WHITE, 0, 10);

		// Pawns (Row 1)
		for (int col = 3; col <= 10; col++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.WHITE, 1, col);
		}

		// --- North Player (WHITE) ---
		// Back Rank (Row 13)
		layout.addPiece(Piece.Type.ROOK, Piece.Color.WHITE, 13, 3);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.WHITE, 13, 4);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.WHITE, 13, 5);
		layout.addPiece(Piece.Type.KING, Piece.Color.WHITE, 13, 6);
		layout.addPiece(Piece.Type.QUEEN, Piece.Color.WHITE, 13, 7);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.WHITE, 13, 8);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.WHITE, 13, 9);
		layout.addPiece(Piece.Type.ROOK, Piece.Color.WHITE, 13, 10);

		// Pawns (Row 12)
		for (int col = 3; col <= 10; col++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.WHITE, 12, col);
		}

		// --- West Player (BLACK) ---
		// Back Rank (Col 0)
		layout.addPiece(Piece.Type.ROOK, Piece.Color.BLACK, 3, 0);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.BLACK, 4, 0);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.BLACK, 5, 0);
		layout.addPiece(Piece.Type.KING, Piece.Color.BLACK, 6, 0);
		layout.addPiece(Piece.Type.QUEEN, Piece.Color.BLACK, 7, 0);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.BLACK, 8, 0);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.BLACK, 9, 0);
		layout.addPiece(Piece.Type.ROOK, Piece.Color.BLACK, 10, 0);

		// Pawns (Col 1)
		for (int row = 3; row <= 10; row++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.BLACK, row, 1);
		}

		// --- East Player (BLACK) ---
		// Back Rank (Col 13)
		layout.addPiece(Piece.Type.ROOK, Piece.Color.BLACK, 3, 13);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.BLACK, 4, 13);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.BLACK, 5, 13);
		layout.addPiece(Piece.Type.KING, Piece.Color.BLACK, 6, 13);
		layout.addPiece(Piece.Type.QUEEN, Piece.Color.BLACK, 7, 13);
		layout.addPiece(Piece.Type.BISHOP, Piece.Color.BLACK, 8, 13);
		layout.addPiece(Piece.Type.KNIGHT, Piece.Color.BLACK, 9, 13);
		layout.addPiece(Piece.Type.ROOK, Piece.Color.BLACK, 10, 13);

		// Pawns (Col 12)
		for (int row = 3; row <= 10; row++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.BLACK, row, 12);
		}

		return layout;
	}
}