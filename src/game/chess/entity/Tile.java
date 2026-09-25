package game.chess.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import tools.Helper;

public class Tile {

	public enum Type {
		NORMAL,
		DISABLED;
	}

// ======================================================================================================================================================

	private static final Color[] TILE_COLORS = {
		Helper.getColorFromHex("#EBECD0"),
		Helper.getColorFromHex("#779556")
	};

	private double x = 0;
	private double y = 0;
	private int row = 0;
	private int col = 0;
	private double size = 0;
	private Tile.Type type = Tile.Type.NORMAL;
	private Piece piece = null;

// ======================================================================================================================================================

	public Tile(int row, int col, double size) {
		this.row = row;
		this.col = col;
		this.size = size;
	}

// ======================================================================================================================================================

	public void init() {
	}

	public void tick(double elapsedSecond) {
	}

	public void render(Graphics2D g) {
		switch(this.type) {
			case NORMAL:
				this.renderNormal(g);
				break;
			case DISABLED:
				this.renderDisabled(g);
				break;
			default:
				this.renderNormal(g);
				break;
		}
	}

// ======================================================================================================================================================

	private void renderNormal(Graphics2D g) {
		g.setColor(Tile.TILE_COLORS[(this.row + this.col) % 2]);
		g.fillRect(
			(int) this.x,
			(int) this.y,
			(int) this.size,
			(int) this.size
		);
	}

	private void renderDisabled(Graphics2D g) {
	}

// ======================================================================================================================================================

	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void disable() {
		this.type = Tile.Type.DISABLED;
	}

	public void place(Piece piece) {
		if (this.type == Tile.Type.DISABLED) {
			throw new IllegalStateException("Cannot place a piece on a disabled tile");
		}
		this.piece = piece;
		this.piece.setPos(this.x, this.y);
	}
}