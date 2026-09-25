package game.chess.layout;

import game.chess.entity.Piece;

public class LayoutEntry {
	private Piece.Type type;
	private Piece.Color color;
	private int row;
	private int col;

	public LayoutEntry(Piece.Type type, Piece.Color color, int row, int col) {
		this.type = type;
		this.color = color;
		this.row = row;
		this.col = col;
	}

	public Piece.Type getType() {
		return this.type;
	}

	public Piece.Color getColor() {
		return this.color;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
}