import engine.Main;
import engine.GameLoop;
import engine.gamestate.GameStateHandler;

import core.PlayState;

public class App {

// ======================================================================================================================================================

	public static void init() {
		Main.VSYNC(false);
		GameLoop.setTargetFPS(60);
		GameStateHandler.setDefaultStateID(PlayState.ID);
	}

// ======================================================================================================================================================
}
