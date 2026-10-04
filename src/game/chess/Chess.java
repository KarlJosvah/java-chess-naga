package game.chess;

import java.util.List;
import java.util.ArrayList;

import java.awt.Graphics2D;

import game.chess.entity.Tile;
import game.chess.entity.Board;
import game.chess.entity.Piece;
import game.chess.layout.Layout;
import game.chess.utils.Position;
import game.chess.utils.ChessUtils;
import game.chess.factory.BoardFactory;
import game.chess.factory.LayoutFactory;

import engine.Main;

public class Chess {

	public enum Type {
		CLASSIC,
		REVERSED,
		RANDOM,
		GIANT,
		FOUR_PLAYER,
		TEST,
		CUSTOM;
	}

// ======================================================================================================================================================
	
	public static final int WIDTH = Main.WIDTH;
	public static final int HEIGHT = Main.HEIGHT;

	private Board board = null;
	private Layout layout = null;
	private final List<Piece> pieces = new ArrayList<>();
	private Chess.Type type = Chess.Type.CLASSIC;

	private Piece selectedPiece = null;

// ======================================================================================================================================================

	public Chess() {
		this(Chess.Type.CLASSIC);
	}

	public Chess(Type type) {
		this.type = type;
		this.board = BoardFactory.of(this.type);
	}

// ======================================================================================================================================================

	public void init() {
		this.board.init();
		this.layout = LayoutFactory.of(this.type);
		ChessUtils.placePieces(this.board, this.layout, this.pieces);
	}

	private boolean isAnimating = false;
	private Piece attackerPiece = null;
	private Piece targetPiece = null;
	private Tile sourceTile = null;
	private Tile targetTile = null;
	private double animTimer = 0.0;
	private int animStage = 0; // 0: None, 1: Move attacker, 2: Rotate target
	private int startX, startY, targetX, targetY;
	private static final double MOVE_DURATION = 0.35; // Seconds
	private static final double ROTATE_DURATION = 0.30; // Seconds

// ======================================================================================================================================================

	public void tick(double elapsedSecond, long loopID) {
		this.board.tick(elapsedSecond);
		for (Piece piece: this.pieces) {
			piece.tick(elapsedSecond);
		}
		this.tickCaptureAnimation(elapsedSecond);
	}

	private void tickCaptureAnimation(double elapsedSecond) {
		if (!this.isAnimating) {
			return;
		}

		this.animTimer += elapsedSecond;

		if (this.animStage == 1) {
			// Stage 1: Move attacker piece straight line to target piece coordinate
			double progress = Math.min(1.0, this.animTimer / MOVE_DURATION);
			int curX = (int) (this.startX + (this.targetX - this.startX) * progress);
			int curY = (int) (this.startY + (this.targetY - this.startY) * progress);
			this.attackerPiece.setCoordinate(new game.chess.utils.Coordinate(curX, curY));

			if (progress >= 1.0) {
				this.animStage = 2;
				this.animTimer = 0.0;
			}
		} else if (this.animStage == 2) {
			// Stage 2: Target piece falls (rotate 90 degrees clockwise)
			double progress = Math.min(1.0, this.animTimer / ROTATE_DURATION);
			double radians = Math.toRadians(90.0 * progress);
			this.targetPiece.setRenderRotation(radians);

			if (progress >= 1.0) {
				// Animation finished: Swap target tile piece to attacker, free target piece
				if (this.sourceTile != null) {
					this.sourceTile.clearPiece();
				}
				this.targetTile.place(this.attackerPiece);
				this.pieces.remove(this.targetPiece);

				// Reset animation state and re-enable inputs
				this.attackerPiece = null;
				this.targetPiece = null;
				this.sourceTile = null;
				this.targetTile = null;
				this.animStage = 0;
				this.isAnimating = false;
			}
		}
	}

	public void render(Graphics2D g, int renderWidth, int renderHeight) {
		this.board.render(g);
		for (Piece piece: this.pieces) {
			piece.render(g);
		}
		this.board.renderHighlight(g);
	}

// ======================================================================================================================================================

	public void handleLeftClick(int x, int y) {
		if (this.isAnimating) {
			return;
		}
		try {
			this.handleLeftClick_(x, y);
		} catch (NullPointerException e) {
			this.clearSelection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleLeftClick_(int x, int y) {
		Tile clickedTile = this.board.getTileAtPixel(x, y);
		Piece clickedPiece = clickedTile.getPiece();

		if (this.selectedPiece != null && clickedPiece != null && this.selectedPiece.isEnemyPiece(clickedPiece)) {
			Tile attackerTile = this.selectedPiece.getTile();
			this.clearSelection();
			this.capturePiece(attackerTile, clickedTile);
		} else {
			this.clearSelection();
			if (clickedPiece != null) {
				clickedTile.select();
				this.clickPiece(clickedPiece);
			}
		}
	}

	public void handleRightClick(int x, int y) {
		if (this.isAnimating) {
			return;
		}
		Tile clickedTile = this.board.getTileAtPixel(x, y);
		System.out.println(clickedTile);
	}

// ======================================================================================================================================================

	private void clearSelection() {
		this.board.clearSelection();
		this.selectedPiece = null;
	}

	private void clickPiece(Piece clickedPiece) {
		this.selectedPiece = clickedPiece;
		List<Position> validMoves = this.selectedPiece.getValidMoves(this.board);
		this.board.highlightTiles(validMoves);
	}

	private void capturePiece(Tile sourceTile, Tile targetTile) {
		this.isAnimating = true;
		this.animStage = 1;
		this.animTimer = 0.0;
		this.sourceTile = sourceTile;
		this.targetTile = targetTile;
		this.attackerPiece = sourceTile.getPiece();
		this.targetPiece = targetTile.getPiece();

		this.startX = this.attackerPiece.getCoordinate().getX();
		this.startY = this.attackerPiece.getCoordinate().getY();
		this.targetX = targetTile.getCoordinate().getX();
		this.targetY = targetTile.getCoordinate().getY();
	}
}