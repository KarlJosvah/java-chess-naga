package game.chess.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import tools.Helper;

public class Tile {

// ======================================================================================================================================================

	private static final Color[] TILE_COLORS = {
		Helper.getColorFromHex("#EBECD0"),
		Helper.getColorFromHex("#779556")
	};

	public static double TILE_SIZE = 0;

	public static void TILE_SIZE(double size) {
		Tile.TILE_SIZE = size;
	}

	private double x = 0;
	private double y = 0;
	private int row = 0;
	private int col = 0;

// ======================================================================================================================================================

	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
	}

// ======================================================================================================================================================

	public void init() {
	}

	public void tick(double elapsedSecond) {
	}

	public void render(Graphics2D g) {
		g.setColor(Tile.TILE_COLORS[(this.row + this.col) % 2]);
		g.fillRect(
			(int) this.x,
			(int) this.y,
			(int) Tile.TILE_SIZE,
			(int) Tile.TILE_SIZE
		);
	}

// ======================================================================================================================================================

	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
}