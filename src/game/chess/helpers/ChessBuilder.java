package game.chess.helpers;

import java.awt.Rectangle;
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

	public static Rectangle boardRect() {
		double availWidth = Chess.WIDTH - (Board.MARGIN * 2);
		double availHeight = Chess.HEIGHT - (Board.MARGIN * 2);

		double maxTileW = availWidth / Board.COL_COUNT;
		double maxTileH = availHeight / Board.ROW_COUNT;
		int tileSize = (int) Math.min(maxTileW, maxTileH);

		int w = tileSize * Board.COL_COUNT;
		int h = tileSize * Board.ROW_COUNT;
		int x = (Chess.WIDTH - w) / 2;
		int y = (Chess.HEIGHT - h) / 2;

		return new Rectangle(x, y, w, h);
	}
}