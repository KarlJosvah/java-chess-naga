package game.chess.factory;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import game.chess.Chess;
import game.chess.entity.Piece;
import game.chess.layout.Layout;

public class LayoutFactory {

// ======================================================================================================================================================

	public static Layout of(Chess.Type type) {
		switch (type) {
			case CLASSIC:
				return LayoutFactory.ofClassic();
			case GIANT:
				return LayoutFactory.ofGiant();
			case FOUR_PLAYER:
				return LayoutFactory.ofFourPlayer();
			case TEST:
				return LayoutFactory.ofTest();
			default:
				return LayoutFactory.ofClassic();
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

	private static Layout ofGiant() {
		Layout layout = new Layout(Chess.Type.GIANT, 20, 20);

		// White Pieces at the South side (Rows 0-1)
		Piece.Type[] mainRank = {
			Piece.Type.ROOK,	Piece.Type.KNIGHT,	Piece.Type.BISHOP,	Piece.Type.BISHOP,	Piece.Type.KNIGHT,
			Piece.Type.ROOK,	Piece.Type.KNIGHT,	Piece.Type.BISHOP,	Piece.Type.KNIGHT,	Piece.Type.QUEEN,
			Piece.Type.KING,	Piece.Type.KNIGHT,	Piece.Type.BISHOP,	Piece.Type.KNIGHT,	Piece.Type.ROOK,
			Piece.Type.KNIGHT,	Piece.Type.BISHOP,	Piece.Type.BISHOP,	Piece.Type.KNIGHT,	Piece.Type.ROOK
		};

		for (int col = 0; col < 20; col++) {
			layout.addPiece(mainRank[col], Piece.Color.WHITE, 0, col);
			layout.addPiece(Piece.Type.PAWN, Piece.Color.WHITE, 1, col);
		}

		// Black Pieces at the North side (Rows 18-19)
		for (int col = 0; col < 20; col++) {
			layout.addPiece(Piece.Type.PAWN, Piece.Color.BLACK, 18, col);
			layout.addPiece(mainRank[col], Piece.Color.BLACK, 19, col);
		}

		return layout;
	}

	private static Layout ofTest() {
		int rowCount = 20;
		int colCount = 20;
		Layout layout = new Layout(Chess.Type.TEST, rowCount, colCount);
		int nbPieceEachSide = 100;

		// Build a list of all available grid positions and shuffle them
		List<int[]> availablePositions = new ArrayList<>();
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < colCount; c++) {
				availablePositions.add(new int[]{r, c});
			}
		}
		Collections.shuffle(availablePositions);

		int posIndex = 0;

		// Non-king piece types available for random selection
		Piece.Type[] nonKingTypes = {
			Piece.Type.PAWN,
			Piece.Type.ROOK,
			Piece.Type.KNIGHT,
			Piece.Type.BISHOP,
			Piece.Type.QUEEN
		};

		for (Piece.Color color : Piece.Color.values()) {
			// 1. Place exactly one King for this side
			if (posIndex < availablePositions.size()) {
				int[] pos = availablePositions.get(posIndex++);
				layout.addPiece(Piece.Type.KING, color, pos[0], pos[1]);
			}

			// 2. Place remaining nbPieceEachSide pieces randomly across remaining types
			for (int i = 0; i < nbPieceEachSide; i++) {
				if (posIndex >= availablePositions.size()) {
					break;
				}
				Piece.Type type = nonKingTypes[i % nonKingTypes.length];
				int[] pos = availablePositions.get(posIndex++);
				layout.addPiece(type, color, pos[0], pos[1]);
			}
		}

		return layout;
	}
}