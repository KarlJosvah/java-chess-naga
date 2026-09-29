package game.chess.entity;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;

import tools.Helper;
import engine.tools.AssetsLoader;

import game.chess.helper.Drawer;
import game.chess.config.Config;
import game.chess.utils.Position;
import game.chess.utils.Rectangle;
import game.chess.utils.Coordinate;

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

	private Coordinate coordinate;
	private Position position;
	private String file = "";
	private String rank = "";
	private int size = 0;

	private Tile.Type type = Tile.Type.NORMAL;
	private Piece piece = null;

// ======================================================================================================================================================

	public Tile(Position position, int size) {
		this.position = position;
		this.size = size;
	}

// ======================================================================================================================================================

	public void init() {
	}

	public void tick(double elapsedSecond) {
	}

	public void render(Graphics2D g) {
		switch (this.type) {
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
		g.setColor(Tile.TILE_COLORS[(this.position.getRow() + this.position.getCol() + 1) % 2]);
		g.fillRect(
			this.coordinate.getX(),
			this.coordinate.getY(),
			this.size,
			this.size
		);
		this.renderAnnotation(g);
	}

	private void renderDisabled(Graphics2D g) {
		// Do not render
	}

// ======================================================================================================================================================

	private void renderAnnotation(Graphics2D g) {
		g.setColor(Tile.TILE_COLORS[(this.position.getRow() + this.position.getCol()) % 2]);

		int paddingX = 4;
		int paddingY = (int) (this.size * 0.25);
		if (AssetsLoader.font_android_101 != null) {
			g.setFont(AssetsLoader.font_android_101.deriveFont(Font.BOLD, 16f));
		}

		if (Config.get().getTileAnnotation() == Config.TileAnnotation.ALL) {
			Drawer.drawStringAnchored(g, this.getAnnotation(), this.getRect(), Drawer.Anchor.TOP_RIGHT, 2);
		} else if (Config.get().getTileAnnotation() == Config.TileAnnotation.BORDER) {
			if (this.position.getCol() == 0) {
				Drawer.drawStringAnchored(g, this.rank, this.getRect(), Drawer.Anchor.TOP_LEFT, 2);
			}
			if (this.position.getRow() == 0) {
				Drawer.drawStringAnchored(g, this.file, this.getRect(), Drawer.Anchor.BOTTOM_RIGHT, 2);
			}
		}
	}

// ======================================================================================================================================================

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public int getRow() {
		return this.position.getRow();
	}

	public int getCol() {
		return this.position.getCol();
	}

	public Tile.Type getType() {
		return this.type;
	}

	public Rectangle getRect() {
		return new Rectangle(
			this.coordinate.getX(),
			this.coordinate.getY(),
			this.size,
			this.size
		);
	}

	public Piece getPiece() {
		return this.piece;
	}

	public boolean isEnemyPiece(Tile anotherTile) {
		return this.getPiece().isEnemyPiece(anotherTile.getPiece());
	}

// ======================================================================================================================================================

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
		this.piece.setTile(this);
		this.piece.setPosition(this.position.copy());
		this.piece.setCoordinate(this.coordinate.copy());
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