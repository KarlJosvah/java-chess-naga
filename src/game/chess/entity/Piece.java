package game.chess.entity;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.chess.entity.Tile;
import game.chess.entity.Board;
import game.chess.utils.Position;
import game.chess.utils.Coordinate;
import game.chess.movement.MoveBehavior;

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

	private Position position;
	private Coordinate coordinate;
	private Tile tile;
	private Piece.Type type;
	private Piece.Color color;
	private BufferedImage sprite;
	private final List<MoveBehavior> behaviors;

// ======================================================================================================================================================

	public Piece() {
		this(Piece.Type.PAWN, Piece.Color.BLACK);
	}

	public Piece(Piece.Type type, Piece.Color color) {
		this.type = type;
		this.color = color;
		this.behaviors = new ArrayList<>();
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

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public Piece.Type getType() {
		return this.type;
	}

	public Piece.Color getColor() {
		return this.color;
	}

// ======================================================================================================================================================

	public void addBehavior(MoveBehavior behavior) {
		this.behaviors.add(behavior);
	}

	public void addAllBehaviors(List<MoveBehavior> behaviors) {
		this.behaviors.addAll(behaviors);
	}

	public Set<Position> getValidMoves(Board board) {
		Set<Position> validMoves = new HashSet<>();

		for (MoveBehavior behavior : this.behaviors) {
			validMoves.addAll(behavior.getPossibleMoves(this.position, board));
		}

		return validMoves;
	}

// ======================================================================================================================================================

	public boolean isEnemyPiece(Piece anotherPiece) {
		return this.getColor() == anotherPiece.getColor();
	}

	public String toString() {
		return "" + this.getType().name() + " " + this.getColor().name();
	}
}