package game.chess.config;

public final class Config {

// ======================================================================================================================================================

	private static final Config instance = new Config();

	private volatile Config.TileAnnotation tileAnnotation = Config.TileAnnotation.BORDER;
	private volatile Config.WindowMode windowMode = Config.WindowMode.FULLSCREEN;
	private volatile Config.WhitePieceBorder whitePieceBorder = Config.WhitePieceBorder.RENDER_OFFSET;

	private volatile boolean enableParticle = false;
	private volatile double particleRadiusRateFromTileSize = 15.0;
	private volatile double particleSpawnDelay = 0.2;
	private volatile double particleMaxDistanceFromTileSize = 0.75;
	private volatile int particleDefaultAlpha = 100;

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

	public enum WhitePieceBorder {
		RENDER_OFFSET,
		SVG_DOM_STROKE
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

	public synchronized Config setWhitePieceBorder(Config.WhitePieceBorder whitePieceBorder) {
		this.whitePieceBorder = whitePieceBorder;
		return this;
	}

	public synchronized Config setEnableParticle(boolean enable) {
		this.enableParticle = enable;
		return this;
	}

	public synchronized Config setParticleRadiusRateFromTileSize(double radiusRate) {
		this.particleRadiusRateFromTileSize = Math.max(0.0, Math.min(100.0, radiusRate));
		return this;
	}

	public synchronized Config setParticleSpawnDelay(double delay) {
		this.particleSpawnDelay = Math.max(0.0, Math.min(1.0, delay));
		return this;
	}

	public synchronized Config setParticleMaxDistanceFromTileSize(double coeff) {
		this.particleMaxDistanceFromTileSize = Math.max(0.0, coeff);
		return this;
	}

	public synchronized Config setParticleDefaultAlpha(int alpha) {
		this.particleDefaultAlpha = Math.max(0, Math.min(255, alpha));
		return this;
	}

// ======================================================================================================================================================

	public Config.TileAnnotation getTileAnnotation() {
		return this.tileAnnotation;
	}

	public Config.WindowMode getWindowMode() {
		return this.windowMode;
	}

	public Config.WhitePieceBorder getWhitePieceBorder() {
		return this.whitePieceBorder;
	}

	public boolean getEnableParticle() {
		return this.enableParticle;
	}

	public double getParticleRadiusRateFromTileSize() {
		return this.particleRadiusRateFromTileSize;
	}

	public double getParticleSpawnDelay() {
		return this.particleSpawnDelay;
	}

	public double getParticleMaxDistanceFromTileSize() {
		return this.particleMaxDistanceFromTileSize;
	}

	public int getParticleDefaultAlpha() {
		return this.particleDefaultAlpha;
	}

// ======================================================================================================================================================

	public synchronized void toggleParticle() {
		this.setEnableParticle(!this.getEnableParticle());
	}

// ======================================================================================================================================================

	public synchronized void defaultConfig() {
		this.setTileAnnotation(Config.TileAnnotation.BORDER)
			.setWindowMode(Config.WindowMode.FULLSCREEN)
			.setWhitePieceBorder(Config.WhitePieceBorder.RENDER_OFFSET)
			.setEnableParticle(false)
			.setParticleRadiusRateFromTileSize(15.0)
			.setParticleSpawnDelay(0.2)
			.setParticleMaxDistanceFromTileSize(0.75)
			.setParticleDefaultAlpha(100)
		;
	}

	public synchronized void testConfig() {
		this.setTileAnnotation(Config.TileAnnotation.ALL)
			.setWindowMode(Config.WindowMode.BORDERLESS)
			.setWhitePieceBorder(Config.WhitePieceBorder.SVG_DOM_STROKE)
			.setEnableParticle(true)
			.setParticleRadiusRateFromTileSize(25.0)
			.setParticleSpawnDelay(0.08)
			.setParticleMaxDistanceFromTileSize(1.5)
			.setParticleDefaultAlpha(200)
		;
	}
}