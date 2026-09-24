package game.chess;

import java.util.ArrayList;

import java.awt.Graphics2D;

import game.chess.entity.Board;
import game.chess.entity.Piece;
import game.chess.helpers.ChessBuilder;
import game.chess.helpers.BoardBuilder;

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
	private ArrayList<Piece> pieces = null;
	private Chess.Type type = Chess.Type.CLASSIC;

// ======================================================================================================================================================

	public Chess() {
		this(Chess.Type.CLASSIC);
	}

	public Chess(Type type) {
		this.type = type;
		this.board = BoardBuilder.of(type);
	}

// ======================================================================================================================================================

	public void init() {
		this.board.init();

		this.pieces = ChessBuilder.buildPieces();
		ChessBuilder.placePieces(this.pieces);
	}

	public void tick(double elapsedSecond, long loopID) {
		this.board.tick(elapsedSecond);
		for(Piece piece: this.pieces) {
			piece.tick(elapsedSecond);
		}
	}

	public void render(Graphics2D g, int renderWidth, int renderHeight) {
		this.board.render(g);
		for(Piece piece: this.pieces) {
			piece.render(g);
		}
	}
}