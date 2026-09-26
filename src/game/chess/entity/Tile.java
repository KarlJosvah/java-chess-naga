package game.chess.entity;

import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

import tools.Helper;
import engine.tools.AssetsLoader;
import game.chess.helper.Drawer;
import game.chess.config.Config;

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
	private String file = "";
	private String rank = "";
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
		g.setColor(Tile.TILE_COLORS[(this.row + this.col + 1) % 2]);
		g.fillRect(
			(int) this.x,
			(int) this.y,
			(int) this.size,
			(int) this.size
		);
		this.renderAnnotation(g);
	}

	private void renderDisabled(Graphics2D g) {
		// Do not render
	}

// ======================================================================================================================================================

	private void renderAnnotation(Graphics2D g) {
		g.setColor(Tile.TILE_COLORS[(this.row + this.col) % 2]);

		int paddingX = 4;
		int paddingY = (int) (this.size * 0.25);
		if (AssetsLoader.font_android_101 != null) {
			g.setFont(AssetsLoader.font_android_101.deriveFont(Font.BOLD, 16f));
		}

		if (Config.get().getTileAnnotation() == Config.TileAnnotation.ALL) {
			Drawer.drawStringAnchored(g, this.getAnnotation(), this.getRect(), Drawer.Anchor.TOP_RIGHT, 2);
		} else if (Config.get().getTileAnnotation() == Config.TileAnnotation.BORDER) {
			if (this.col == 0) {
				Drawer.drawStringAnchored(g, this.rank, this.getRect(), Drawer.Anchor.TOP_LEFT, 2);
			}
			if (this.row == 0) {
				Drawer.drawStringAnchored(g, this.file, this.getRect(), Drawer.Anchor.BOTTOM_RIGHT, 2);
			}
		}
	}

// ======================================================================================================================================================

	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public Rectangle getRect() {
		return new Rectangle(
			(int) this.x,
			(int) this.y,
			(int) this.size,
			(int) this.size
		);
	}

	public void annotate(String file, String rank) {
		this.file = file;
		this.rank = rank;
	}

	public String getAnnotation() {
		return this.file + this.rank;
	}

// ======================================================================================================================================================

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

// ======================================================================================================================================================

	public String toString() {
		String str = "" + this.file + this.rank;
		if (this.piece != null) {
			str += " - " + this.piece;
		}
		return str;
	}
}