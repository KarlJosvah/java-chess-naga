package game.chess.entity;

import java.util.ArrayList;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import game.chess.builder.BoardBuilder;
import game.chess.Chess;

public class Board {
	private int margin = 0;
	private int rowCount = 0;
	private int colCount = 0;

	private Tile[][] tiles = null;
	private Rectangle rect = null;
	private ArrayList<Point> disableMap = new ArrayList<Point>();
	private double tileSize = 0;

// ======================================================================================================================================================

	public Board() {
	}

	public void disable(int x, int y) {
		this.disableMap.add(new Point(x, y));
	}

// ======================================================================================================================================================

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

// ======================================================================================================================================================

	public int getRowCount() {
		return this.rowCount;
	}

	public int getColCount() {
		return this.colCount;
	}

	public double getTileSize() {
		return this.tileSize;
	}

// ======================================================================================================================================================

	public void init() {
		this.rect = BoardBuilder.boardRect(this.rowCount, this.colCount, this.margin);
		this.tileSize = this.rect.getWidth() / this.colCount;
		this.tiles = BoardBuilder.buildTiles(this.rowCount, this.colCount, this.rect, this.tileSize);
		BoardBuilder.disable(this.tiles, this.disableMap);
	}

	public void tick(double elapsedSecond) {
		for(int row = 0; row < this.rowCount; row++) {
			for(int col = 0; col < this.colCount; col++) {
				this.tiles[row][col].tick(elapsedSecond);
			}
		}
	}

	public void render(Graphics2D g) {
		for(int row = 0; row < this.rowCount; row++) {
			for(int col = 0; col < this.colCount; col++) {
				this.tiles[row][col].render(g);
			}
		}
	}

// ======================================================================================================================================================

	public void place(Piece piece, int row, int col) {
		this.tiles[row][col].place(piece);
	}
}