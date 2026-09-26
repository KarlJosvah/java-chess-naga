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

	public static final StateID ID = StateID.of("PLAY");

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
		this.chess = new Chess(Chess.Type.CLASSIC);
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
}