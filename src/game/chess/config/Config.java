package game.chess.config;

public final class Config {

// ======================================================================================================================================================

	private static final Config instance = new Config();

	private volatile Config.TileAnnotation tileAnnotation = Config.TileAnnotation.BORDER;
	private volatile Config.WindowMode windowMode = Config.WindowMode.FULLSCREEN;

// ======================================================================================================================================================

	public enum TileAnnotation {
		ALL,
		BORDER
	}

	public enum WindowMode {
		FULLSCREEN,
		WINDOWED,
		BORDERLESS
	}

// ======================================================================================================================================================

	private Config() {
	}

	public static Config get() {
		return Config.instance;
	}

// ======================================================================================================================================================

	public synchronized Config setTileAnnotation(Config.TileAnnotation tileAnnotation) {
		this.tileAnnotation = tileAnnotation;
		return this;
	}

	public synchronized Config setWindowMode(Config.WindowMode windowMode) {
		this.windowMode = windowMode;
		return this;
	}

	public Config.TileAnnotation getTileAnnotation() {
		return this.tileAnnotation;
	}

	public Config.WindowMode getWindowMode() {
		return this.windowMode;
	}

// ======================================================================================================================================================

	public synchronized void defaultConfig() {
		this.setTileAnnotation(Config.TileAnnotation.BORDER)
			.setWindowMode(Config.WindowMode.FULLSCREEN);
	}

	public synchronized void testConfig() {
		this.setTileAnnotation(Config.TileAnnotation.ALL)
			.setWindowMode(Config.WindowMode.BORDERLESS);
	}
}