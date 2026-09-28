package game.chess.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import game.chess.utils.Coordinate;

public class Piece {

	public enum Type {
		PAWN,
		ROOK,
		KNIGHT,
		BISHOP,
		QUEEN,
		KING;
	}

	public enum Color {
		BLACK,
		WHITE;
	}

// ======================================================================================================================================================

	private Coordinate coordinate;
	private Piece.Type type;
	private Piece.Color color;
	private BufferedImage sprite;

// ======================================================================================================================================================

	public Piece() {
		this(Piece.Type.PAWN, Piece.Color.BLACK);
	}

	public Piece(Piece.Type type, Piece.Color color) {
		this.type = type;
		this.color = color;
	}

// ======================================================================================================================================================

	public void init() {
	}

	public void tick(double elapsedSecond) {
	}

	public void render(Graphics2D g) {
		g.drawImage(
			this.sprite,
			this.coordinate.getX(),
			this.coordinate.getY(),
			null
		);
	}

// ======================================================================================================================================================

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public String toString() {
		return "" + this.type.name() + " " + this.color.name();
	}
}