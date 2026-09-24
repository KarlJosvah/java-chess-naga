package game.chess.helpers;

import java.util.ArrayList;

import game.chess.Chess;
import game.chess.entity.Board;
import game.chess.entity.Piece;

public class ChessBuilder {

	public static ArrayList<Piece> buildPieces() {
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		for(Piece piece: pieces) {
			piece = new Piece();
			piece.init();
		}
		return pieces;
	}

	public static void placePieces(ArrayList<Piece> pieces) {
		int x = 0;
		int y = 0;
		for(Piece piece: pieces) {
			piece.setPos(x, y);
		}
	}
}