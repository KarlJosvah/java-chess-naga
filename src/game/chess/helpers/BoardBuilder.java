package game.chess.helpers;

import java.util.ArrayList;

import java.awt.Point;
import java.awt.Rectangle;

import game.chess.Chess;
import game.chess.entity.Board;
import game.chess.entity.Tile;

public class BoardBuilder {

// ======================================================================================================================================================

	public static Board of(Chess.Type type) {
		switch (type) {
			case CLASSIC:
				return BoardBuilder.ofClassic();
			case GIANT:
				return BoardBuilder.ofGiant();
			case FOUR_PLAYER:
				return BoardBuilder.ofFourPlayer();
			default:
				return BoardBuilder.ofClassic();
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
		for(int row = 0; row < crease; row++) {
			for(int col = 0; col < crease; col++) {
				board.disable(row, col);
			}
		}

		// Disable TOP-RIGHT
		for(int row = 0; row < crease; row++) {
			for(int col = colCount - 1; col >= colCount - crease; col--) {
				board.disable(row, col);
			}
		}

		// Disable BOTTOM-LEFT
		for(int row = rowCount - 1; row >= rowCount - crease; row--) {
			for(int col = 0; col < crease; col++) {
				board.disable(row, col);
			}
		}

		// Disable BOTTOM-RIGHT
		for(int row = rowCount - 1; row >= rowCount - crease; row--) {
			for(int col = colCount - 1; col >= colCount - crease; col--) {
				board.disable(row, col);
			}
		}

		return board;
	}

// ======================================================================================================================================================

	public static Rectangle boardRect(int rowCount, int colCount, int margin) {
		double availWidth = Chess.WIDTH - (margin * 2);
		double availHeight = Chess.HEIGHT - (margin * 2);

		double maxTileW = availWidth / colCount;
		double maxTileH = availHeight / rowCount;
		int tileSize = (int) Math.min(maxTileW, maxTileH);

		int w = tileSize * colCount;
		int h = tileSize * rowCount;
		int x = (Chess.WIDTH - w) / 2;
		int y = (Chess.HEIGHT - h) / 2;

		return new Rectangle(x, y, w, h);
	}

	public static Tile[][] buildTiles(int rowCount, int colCount, Rectangle rect) {
		Tile[][] tiles = new Tile[rowCount][colCount];

		double startX = rect.getX();
		double startY = rect.getY();

		for(int row = 0; row < rowCount; row++) {
			for(int col = 0; col < colCount; col++) {
				tiles[row][col] = new Tile(row, col);
				tiles[row][col].setPos(startX + (col * Tile.TILE_SIZE), startY + (row * Tile.TILE_SIZE));
			}
		}

		return tiles;
	}

	public static void disable(Tile[][] tiles, ArrayList<Point> disableMap) {
		for(Point p : disableMap) {
			tiles[p.x][p.y].disable();
		}
	}
}