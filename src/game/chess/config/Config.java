package game.chess.config;

public class Config {

// ======================================================================================================================================================

	private static Config instance = Config.defaultConfig();

	public static void set(Config config) {
		Config.instance = config;
	}

	public static Config get() {
		return Config.instance;
	}

// ======================================================================================================================================================

	public enum TileAnnotation {
		ALL,
		BORDER
	}

	public enum WindowMode {
		FULLSCREEN,
		WINDOWED,
		BORDERLESS;
	}

	private Config.TileAnnotation tileAnnotation;
	private Config.WindowMode windowMode;

// ======================================================================================================================================================

	private Config(Builder builder) {
		this.tileAnnotation = builder.tileAnnotation;
		this.windowMode = builder.windowMode;
	}

	public static Config defaultConfig() {
		return new Builder().build();
	}

	public static Config testConfig() {
		return new Builder()
			.setTileAnnotation(Config.TileAnnotation.ALL)
			.setWindowMode(Config.WindowMode.BORDERLESS)
			.build();
	}

// ======================================================================================================================================================

	public static Builder builder() {
		return new Builder();
	}

	public Config.TileAnnotation getTileAnnotation() {
		return this.tileAnnotation;
	}

	public Config.WindowMode getWindowMode() {
		return this.windowMode;
	}

// ======================================================================================================================================================

	public static class Builder {
		private Config.TileAnnotation tileAnnotation = Config.TileAnnotation.BORDER;
		private Config.WindowMode windowMode = Config.WindowMode.FULLSCREEN;

		public Builder setTileAnnotation(Config.TileAnnotation tileAnnotation) {
			this.tileAnnotation = tileAnnotation;
			return this;
		}

		public Builder setWindowMode(Config.WindowMode windowMode) {
			this.windowMode = windowMode;
			return this;
		}

		public Config build() {
			return new Config(this);
		}
	}
}