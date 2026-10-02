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

	public void tick(double elapsedSecond, long loopID) {
		this.board.tick(elapsedSecond);
		for (Piece piece: this.pieces) {
			piece.tick(elapsedSecond);
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
		Tile clickedTile = this.board.getTileAtPixel(x, y);
		this.clearSelection();

		if (clickedTile == null) {
			return;
		}

		Piece clickedPiece = clickedTile.getPiece();
		if (clickedPiece != null) {
			this.clickPiece(clickedPiece);
		}
	}

	public void handleRightClick(int x, int y) {
		Tile clickedTile = this.board.getTileAtPixel(x, y);
		System.out.println(clickedTile);
	}

// ======================================================================================================================================================

	private void clearSelection() {
		this.board.clearSelection();
	}

	private void clickPiece(Piece clickedPiece) {
		List<Position> validMoves = clickedPiece.getValidMoves(this.board);
		this.board.highlightTiles(validMoves);
	}
}