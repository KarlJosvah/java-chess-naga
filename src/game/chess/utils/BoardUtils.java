package game.chess.utils;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import game.chess.Chess;
import game.chess.entity.Tile;

public class BoardUtils {
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

	public static Tile[][] buildTiles(int rowCount, int colCount, Rectangle rect, double tileSize) {
		Tile[][] tiles = new Tile[rowCount][colCount];

		double startX = rect.getX();
		double startY = rect.getY();

		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				tiles[row][col] = new Tile(row, col, tileSize);
				
				// Calculate Y position so row 0 is at the bottom and rowCount - 1 is at the top
				double posX = startX + (col * tileSize);
				double posY = startY + ((rowCount - 1 - row) * tileSize);
				
				tiles[row][col].setPos(posX, posY);
				BoardUtils.annotate(tiles[row][col]);
			}
		}

		return tiles;
	}

// ======================================================================================================================================================

	public static void disable(Tile[][] tiles, ArrayList<Point> disableMap) {
		for (Point p : disableMap) {
			tiles[p.x][p.y].disable();
		}
	}

// ======================================================================================================================================================

	private static String getFileString(int col) {
		StringBuilder file = new StringBuilder();
		col += 1;

		while(col > 0) {
			col--;
			char c = (char) ('a' + col % 26);
			file.insert(0, c);
			col /= 26;
		}

		return file.toString();
	}

	private static void annotate(Tile tile) {
		String file = BoardUtils.getFileString(tile.getCol());
		String rank = "" + (tile.getRow() + 1);
		tile.annotate(file, rank);
	}
}