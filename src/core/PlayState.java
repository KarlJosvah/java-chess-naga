package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

import engine.gamestate.StateID;
import engine.gamestate.GameState;
import engine.gamestate.InputEvent;
import engine.gamestate.GameStateHandler;

import game.chess.Chess;
import game.chess.entity.Board;

public class PlayState extends GameState {

// ======================================================================================================================================================

	public static final StateID ID = StateID.of("MAIN");

	static {
		GameStateHandler.registerState(PlayState.ID, new PlayState());
	}

// ======================================================================================================================================================

	private Chess chess;

// ======================================================================================================================================================

	public PlayState() {
	}

// ======================================================================================================================================================

	@Override
	public void init() {
		this.chess = new Chess();
		this.chess.init();
	}

	@Override
	public void tick(double elapsedSecond, long loopID) {
		this.chess.tick(elapsedSecond, loopID);
	}

	@Override
	public void render(Graphics2D g, int renderWidth, int renderHeight) {
		this.chess.render(g, renderWidth, renderHeight);
	}

	@Override
	public void closeState() {
	}

// ======================================================================================================================================================

	@Override
	public void input(InputEvent event) {
		int min = 1;
		int max = 50;
		boolean allowChange = false;
		if (event.getType() == InputEvent.Type.KEY_PRESSED && allowChange) {
			switch (event.getKeyCode()) {
				case KeyEvent.VK_UP:
					Board.ROW_COUNT = Math.min(max, Board.ROW_COUNT + 1);
					this.chess.init();
					break;
				case KeyEvent.VK_DOWN:
					Board.ROW_COUNT = Math.max(min, Board.ROW_COUNT - 1);
					this.chess.init();
					break;
				case KeyEvent.VK_RIGHT:
					Board.COL_COUNT = Math.min(max, Board.COL_COUNT + 1);
					this.chess.init();
					break;
				case KeyEvent.VK_LEFT:
					Board.COL_COUNT = Math.max(min, Board.COL_COUNT - 1);
					this.chess.init();
					break;
				default:
					break;
			}
		}
	}
}