package game.chess.utils;

import java.util.ArrayList;

import game.chess.Chess;
import game.chess.entity.Tile;
import game.chess.utils.Position;
import game.chess.utils.Rectangle;
import game.chess.utils.Coordinate;

public class BoardUtils {
	public static Rectangle boardRect(int rowCount, int colCount, int margin) {
		int availWidth = Chess.WIDTH - (margin * 2);
		int availHeight = Chess.HEIGHT - (margin * 2);

		int maxTileW = availWidth / colCount;
		int maxTileH = availHeight / rowCount;
		int tileSize = Math.min(maxTileW, maxTileH);

		int w = tileSize * colCount;
		int h = tileSize * rowCount;
		int x = (Chess.WIDTH - w) / 2;
		int y = (Chess.HEIGHT - h) / 2;

		return new Rectangle(x, y, w, h);
	}

	public static Tile[][] buildTiles(int rowCount, int colCount, Rectangle rect, int tileSize) {
		Tile[][] tiles = new Tile[rowCount][colCount];

		int startX = rect.getX();
		int startY = rect.getY();

		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				tiles[row][col] = new Tile(new Position(row, col), tileSize);
				
				// Calculate Y position so row 0 is at the bottom and rowCount - 1 is at the top
				int posX = startX + (col * tileSize);
				int posY = startY + ((rowCount - 1 - row) * tileSize);
				
				tiles[row][col].setCoordinate(new Coordinate(posX, posY));
				BoardUtils.annotate(tiles[row][col]);
			}
		}

		return tiles;
	}

// ======================================================================================================================================================

	public static void disable(Tile[][] tiles, ArrayList<Position> disabledPosition) {
		for (Position pos : disabledPosition) {
			tiles[pos.getRow()][pos.getCol()].disable();
		}
	}

// ======================================================================================================================================================

	private static String getFileString(int col) {
		StringBuilder file = new StringBuilder();
		col += 1;

		while (col > 0) {
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