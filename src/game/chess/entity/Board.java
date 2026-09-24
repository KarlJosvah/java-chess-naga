package game.chess.entity;

import java.awt.Rectangle;
import java.awt.Graphics2D;

import game.chess.helpers.ChessBuilder;

public class Board {
	public static int ROW_COUNT = 8;
	public static int COL_COUNT = 8;
	public static int MARGIN = 10;

	private Tile[][] tiles = null;
	private Rectangle rect = null;

// ======================================================================================================================================================

	public Board() {
	}

// ======================================================================================================================================================

	public void init() {
		this.rect = ChessBuilder.boardRect();
		Tile.TILE_SIZE(this.rect.getWidth() / Board.COL_COUNT);
		this.initTiles();
	}

	public void tick(double elapsedSecond) {
		for(int row = 0; row < Board.ROW_COUNT; row++) {
			for(int col = 0; col < Board.COL_COUNT; col++) {
				this.tiles[row][col].tick(elapsedSecond);
			}
		}
	}

	public void render(Graphics2D g) {
		for(int row = 0; row < Board.ROW_COUNT; row++) {
			for(int col = 0; col < Board.COL_COUNT; col++) {
				this.tiles[row][col].render(g);
			}
		}
	}

// ======================================================================================================================================================

	public void initTiles() {
		this.tiles = new Tile[Board.ROW_COUNT][Board.COL_COUNT];

		double startX = this.rect.getX();
		double startY = this.rect.getY();

		for(int row = 0; row < Board.ROW_COUNT; row++) {
			for(int col = 0; col < Board.COL_COUNT; col++) {
				this.tiles[row][col] = new Tile(row, col);
				this.tiles[row][col].setPos(startX + (col * Tile.TILE_SIZE), startY + (row * Tile.TILE_SIZE));
			}
		}
	}
}