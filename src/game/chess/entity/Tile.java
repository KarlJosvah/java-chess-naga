package game.chess.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

import tools.Helper;

import game.chess.config.Config;
import game.chess.utils.Position;
import game.chess.utils.Rectangle;
import game.chess.utils.Coordinate;
import game.chess.particle.Particle;
import game.chess.entity.renderer.TileRenderer;

public class Tile {

	public enum Type {
		NORMAL,
		DISABLED,
		POSSIBLE_MOVE,
		CAPTURE,
		FLAG_RED,
		FLAG_GREEN,
		FLAG_BLUE,
		FLAG_ORANGE;
	}

// ======================================================================================================================================================

	private Coordinate coordinate;
	private Position position;
	private String file = "";
	private String rank = "";
	private int size = 0;

	private Tile.Type type = Tile.Type.NORMAL;
	private Piece piece = null;
	private final List<Particle> particles = new ArrayList<>();
	private double particleSpawnTimer = 0;

// ======================================================================================================================================================

	public Tile(Position position, int size) {
		this.position = position;
		this.size = size;
	}

// ======================================================================================================================================================

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public Position getPosition() {
		return this.position;
	}

	public int getRow() {
		return this.position.getRow();
	}

	public int getCol() {
		return this.position.getCol();
	}

	public String getFile() {
		return this.file;
	}

	public String getRank() {
		return this.rank;
	}

	public int getSize() {
		return this.size;
	}

	public Rectangle getRect() {
		return new Rectangle(
			this.coordinate.getX(),
			this.coordinate.getY(),
			this.size,
			this.size
		);
	}

	public Tile.Type getType() {
		return this.type;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public List<Particle> getParticles() {
		return this.particles;
	}

	public boolean isEnemyPiece(Tile anotherTile) {
		return this.getPiece().isEnemyPiece(anotherTile.getPiece());
	}

// ======================================================================================================================================================

	public void init() {
	}

	public void tick(double elapsedSecond) {
		this.tickParticles(elapsedSecond);
	}

	public void render(Graphics2D g) {
		TileRenderer.render(this, g);
	}

	public void renderHighlight(Graphics2D g) {
		TileRenderer.renderHighlight(this, g);
	}

// ======================================================================================================================================================

	private void tickParticles(double elapsedSecond) {
		if (Config.get().getEnableParticle() == false) {
			this.clearParticles();
			return;
		}
		if (this.piece != null && this.piece.getType() == Piece.Type.KING) {
			this.particleSpawnTimer += elapsedSecond;

			this.spawnNewParticle();
			this.updateParticles(elapsedSecond);
		} else {
			this.clearParticles();
		}
	}

	private void spawnNewParticle() {
		if (this.particleSpawnTimer >= Config.get().getParticleSpawnDelay()) {
			this.particleSpawnTimer = 0;
			double angle = Math.random() * Math.PI * 2;
			double maxDistance = this.size * Config.get().getParticleMaxDistanceFromTileSize();

			Color baseColor = (this.piece.getColor() == Piece.Color.WHITE)
				? TileRenderer.PARTICLE_COLORS[0]
				: TileRenderer.PARTICLE_COLORS[1];

			Color particleColor = new Color(
				baseColor.getRed(),
				baseColor.getGreen(),
				baseColor.getBlue(),
				Config.get().getParticleDefaultAlpha()
			);

			this.particles.add(new Particle(angle, maxDistance, particleColor));
		}
	}

	private void updateParticles(double elapsedSecond) {
		for (int i = this.particles.size() - 1; i >= 0; i--) {
			Particle p = this.particles.get(i);
			p.tick(elapsedSecond);
			if (p.isDead()) {
				this.particles.remove(i);
			}
		}
	}

	private void clearParticles() {
		if (!this.particles.isEmpty()) {
			this.particles.clear();
		}
	}

// ======================================================================================================================================================

	public void annotate(String file, String rank) {
		this.file = file;
		this.rank = rank;
	}

	public String getAnnotation() {
		return this.file + this.rank;
	}

// ======================================================================================================================================================

	public void disable() {
		this.type = Tile.Type.DISABLED;
	}

	public void place(Piece piece) {
		if (this.type == Tile.Type.DISABLED) {
			throw new IllegalStateException("Cannot place a piece on a disabled tile");
		}
		this.piece = piece;
		this.piece.setTile(this);
		this.piece.setPosition(this.position.copy());
		this.piece.setCoordinate(this.coordinate.copy());
	}

	public void highlightTile() {
		if (this.piece == null) {
			this.type = Tile.Type.POSSIBLE_MOVE;
		} else {
			this.type = Tile.Type.CAPTURE;
		}
	}

	public void clear() {
		if (this.type == Tile.Type.DISABLED) {
			return;
		}
		this.type = Tile.Type.NORMAL;
	}

// ======================================================================================================================================================

	public String toString() {
		String str = "" + this.file + this.rank;
		if (this.piece != null) {
			str += " - " + this.piece;
		}
		return str;
	}
}