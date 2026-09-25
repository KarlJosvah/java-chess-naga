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
		for(int col = 0; col < 8; col++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.WHITE, 1, col);
		}

		// Black pawns (Row 6)
		for(int col = 0; col < 8; col++) {
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
}