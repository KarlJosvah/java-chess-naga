package game.chess;

import java.util.ArrayList;

import java.awt.Graphics2D;

import game.chess.entity.Board;
import game.chess.entity.Piece;
import game.chess.helpers.ChessBuilder;

import engine.Main;

public class Chess {

// ======================================================================================================================================================

	private Board board = new Board();
	private ArrayList<Piece> pieces = null;
	
	public static final int WIDTH = Main.WIDTH;
	public static final int HEIGHT = Main.HEIGHT;

// ======================================================================================================================================================

	public Chess() {
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