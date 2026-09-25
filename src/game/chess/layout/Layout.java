package game.chess.layout;

import java.util.ArrayList;
import game.chess.Chess;
import game.chess.entity.Piece;

public class Layout {

	private ArrayList<LayoutEntry> entry = new ArrayList<LayoutEntry>();
	private Chess.Type type = null;
	private int rowCount = 0;
	private int colCount = 0;

// ======================================================================================================================================================

	public Layout(Chess.Type type, int rowCount, int colCount) {
		this.type = type;
		this.rowCount = rowCount;
		this.colCount = colCount;
	}

	public void addPiece(Piece.Type type, Piece.Color color, int row, int col) {
		this.entry.add(new LayoutEntry(type, color, row, col));
	}

	public ArrayList<LayoutEntry> getEntries() {
		return this.entry;
	}

	public Chess.Type getType() {
		return this.type;
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public int getColCount() {
		return this.colCount;
	}
}