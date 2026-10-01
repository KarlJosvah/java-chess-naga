package game.chess.entity;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.chess.entity.Tile;
import game.chess.entity.Board;
import game.chess.config.Config;
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
		int px = this.coordinate.getX();
		int py = this.coordinate.getY();

		this.renderWhiteOffset(g, px, py);
		g.drawImage(
			this.sprite,
			px,
			py,
			null
		);
	}

// ======================================================================================================================================================

	private void renderWhiteOffset(Graphics2D g, int px, int py) {
		if (this.color == Piece.Color.WHITE && Config.get().getWhitePieceBorder() == Config.WhitePieceBorder.RENDER_OFFSET) {
			
			// 8-way offset rendering using dark tinted image
			Graphics2D g2d = (Graphics2D) g.create();
			try {
				BufferedImage shadow = new BufferedImage(this.sprite.getWidth(), this.sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D sg = shadow.createGraphics();
				sg.drawImage(this.sprite, 0, 0, null);
				sg.setComposite(java.awt.AlphaComposite.SrcIn);
				sg.setColor(new java.awt.Color(20, 20, 20, 220));
				sg.fillRect(0, 0, shadow.getWidth(), shadow.getHeight());
				sg.dispose();

				g2d.drawImage(shadow, px - 1, py, null);
				g2d.drawImage(shadow, px + 1, py, null);
				g2d.drawImage(shadow, px, py - 1, null);
				g2d.drawImage(shadow, px, py + 1, null);
				g2d.drawImage(shadow, px - 1, py - 1, null);
				g2d.drawImage(shadow, px + 1, py - 1, null);
				g2d.drawImage(shadow, px - 1, py + 1, null);
				g2d.drawImage(shadow, px + 1, py + 1, null);
			} finally {
				g2d.dispose();
			}
		}
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

	public List<Position> getValidMoves(Board board) {
		Set<Position> validMoves = new HashSet<>();

		for (MoveBehavior behavior : this.behaviors) {
			validMoves.addAll(behavior.getPossibleMoves(this.position, board));
		}

		return List.copyOf(validMoves);
	}

// ======================================================================================================================================================

	public boolean isEnemyPiece(Piece anotherPiece) {
		return this.getColor() != anotherPiece.getColor();
	}

	public String toString() {
		return "" + this.getType().name() + " " + this.getColor().name();
	}
}