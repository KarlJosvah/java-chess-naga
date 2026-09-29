package game.chess.entity;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Ellipse2D;

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
		DISABLED,
		POSSIBLE_MOVE,
		CAPTURE,
		FLAG_RED,
		FLAG_GREEN,
		FLAG_BLUE,
		FLAG_ORANGE;
	}

// ======================================================================================================================================================

	public static double POSSIBLE_MOVE_CIRCLE_RADIUS_RATE = 20.0;
	public static double CAPTURE_CIRCLE_RADIUS_RATE = 60.0;
	public static double CAPTURE_CIRCLE_THICHNESS_RATE = 10.0;

	public static Color CIRCLE_COLOR = Helper.getColorFromHex("#4b4b4b40");

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

	public void renderHighlight(Graphics2D g) {
		switch (this.type) {
			case POSSIBLE_MOVE:
				this.renderPossibleMove(g);
				break;
			case CAPTURE:
				this.renderCapture(g);
				break;
			case FLAG_RED:
				this.renderFlagRed(g);
				break;
			case FLAG_GREEN:
				this.renderFlagGreen(g);
				break;
			case FLAG_BLUE:
				this.renderFlagBlue(g);
				break;
			case FLAG_ORANGE:
				this.renderFlagOrange(g);
				break;
			default:
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

	private void renderPossibleMove(Graphics2D g) {
		int radius = (int) Helper.percent(this.size, Tile.POSSIBLE_MOVE_CIRCLE_RADIUS_RATE);
		int x = this.coordinate.getX() + ( (this.size - radius) / 2 );
		int y = this.coordinate.getY() + ( (this.size - radius) / 2 );

		g.setColor(Tile.CIRCLE_COLOR);
		g.fillOval(x, y, radius, radius);
	}

	private void renderCapture(Graphics2D g) {
		int radius = (int) Helper.percent(this.size, Tile.CAPTURE_CIRCLE_RADIUS_RATE);
		float thickness = (int) Helper.percent(this.size, Tile.CAPTURE_CIRCLE_THICHNESS_RATE);
		int x = this.coordinate.getX() + ( (this.size - radius) / 2 );
		int y = this.coordinate.getY() + ( (this.size - radius) / 2 );

		Graphics2D g2d = (Graphics2D) g.create();
		try {
			g2d.setColor(Tile.CIRCLE_COLOR);
			g2d.setStroke(new BasicStroke(thickness));
			Ellipse2D ring = new Ellipse2D.Double(x, y, radius, radius);
			g2d.draw(ring);
		} finally {
			g2d.dispose();
		}
	}

	private void renderFlagRed(Graphics2D g) {
	}

	private void renderFlagGreen(Graphics2D g) {
	}

	private void renderFlagBlue(Graphics2D g) {
	}

	private void renderFlagOrange(Graphics2D g) {
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

	public void highlightTile(Piece.Color color) {
		if (this.piece == null) {
			this.type = Tile.Type.POSSIBLE_MOVE;
		} else {
			this.type = Tile.Type.CAPTURE;
		}
	}

	public void clear() {
		if (this.type == Tile.Type.DISABLED) {
			return;
		}
		this.type = Tile.Type.NORMAL;
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