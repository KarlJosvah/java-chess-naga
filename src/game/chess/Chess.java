package game.chess;

import java.util.ArrayList;

import java.awt.Graphics2D;

import game.chess.entity.Board;
import game.chess.entity.Piece;
import game.chess.layout.Layout;
import game.chess.builder.BoardBuilder;
import game.chess.builder.ChessBuilder;
import game.chess.builder.LayoutBuilder;

import engine.Main;

public class Chess {

	public enum Type {
		CLASSIC,
		REVERSED,
		RANDOM,
		GIANT,
		FOUR_PLAYER,
		CUSTOM;
	}

// ======================================================================================================================================================
	
	public static final int WIDTH = Main.WIDTH;
	public static final int HEIGHT = Main.HEIGHT;

	private Board board = null;
	private Layout layout = null;
	private final ArrayList<Piece> pieces = new ArrayList<Piece>();
	private Chess.Type type = Chess.Type.CLASSIC;

// ======================================================================================================================================================

	public Chess() {
		this(Chess.Type.CLASSIC);
	}

	public Chess(Type type) {
		this.type = type;
		this.board = BoardBuilder.of(this.type);
	}

// ======================================================================================================================================================

	public void init() {
		this.board.init();
		this.layout = LayoutBuilder.of(this.type);
		ChessBuilder.placePieces(this.board, this.layout, this.pieces);
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
	}
}