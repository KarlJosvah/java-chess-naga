import engine.Main;
import engine.GameLoop;
import engine.gamestate.GameStateHandler;

import core.PlayState;
import game.chess.config.Config;

public class App {

// ======================================================================================================================================================

	public static void init() {
		Main.VSYNC(false);
		GameLoop.setTargetFPS(60);
		GameStateHandler.setDefaultStateID(PlayState.ID);
		Config.get().defaultConfig();
	}

// ======================================================================================================================================================
}
