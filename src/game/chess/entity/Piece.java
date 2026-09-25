package game.chess.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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

	private double x = 0;
	private double y = 0;

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
			(int) this.x,
			(int) this.y,
			null
		);
	}

// ======================================================================================================================================================

	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
}