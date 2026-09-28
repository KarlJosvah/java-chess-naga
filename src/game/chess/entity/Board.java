package game.chess.entity;

import java.util.ArrayList;

import java.awt.Graphics2D;

import game.chess.Chess;
import game.chess.utils.Position;
import game.chess.utils.Rectangle;
import game.chess.utils.BoardUtils;
import game.chess.factory.BoardFactory;

public class Board {
	private int margin = 0;
	private int rowCount = 0;
	private int colCount = 0;

	private Tile[][] tiles = null;
	private Rectangle rect = null;
	private ArrayList<Position> disabledPosition = new ArrayList<Position>();
	private int tileSize = 0;

// ======================================================================================================================================================

	public Board() {
	}

	public void disablePosition(Position pos) {
		this.disabledPosition.add(pos);
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

	public int getTileSize() {
		return this.tileSize;
	}

// ======================================================================================================================================================

	public void init() {
		this.rect = BoardUtils.boardRect(this.rowCount, this.colCount, this.margin);
		this.tileSize = this.rect.getWidth() / this.colCount;
		this.tiles = BoardUtils.buildTiles(this.rowCount, this.colCount, this.rect, this.tileSize);
		BoardUtils.disable(this.tiles, this.disabledPosition);
	}

	public void tick(double elapsedSecond) {
		for (int row = 0; row < this.rowCount; row++) {
			for (int col = 0; col < this.colCount; col++) {
				this.tiles[row][col].tick(elapsedSecond);
			}
		}
	}

	public void render(Graphics2D g) {
		for (int row = 0; row < this.rowCount; row++) {
			for (int col = 0; col < this.colCount; col++) {
				this.tiles[row][col].render(g);
			}
		}
	}

// ======================================================================================================================================================

	public void place(Piece piece, int row, int col) {
		this.tiles[row][col].place(piece);
	}

	public Tile getTileAtPixel(int mouseX, int mouseY) {
		int col = (int) ( (mouseX - this.rect.getX()) / this.getTileSize() );
		int row = (int) ( (this.rect.getY() + this.rect.getHeight() - mouseY) / this.getTileSize() );

		if (row >= 0 && row < this.rowCount && col >= 0 && col < this.colCount) {
			return this.tiles[row][col];
		}
		return null;
	}
}