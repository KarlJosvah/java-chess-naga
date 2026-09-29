package game.chess.particle;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {
	private double distance;
	private final double angle;
	private final double maxDistance;
	private final Color color;
	private boolean dead;

	public Particle(double angle, double maxDistance, Color color) {
		this.distance = 0;
		this.angle = angle;
		this.maxDistance = maxDistance;
		this.color = color;
		this.dead = false;
	}

	public void tick(double elapsedSecond) {
		if (this.dead) {
			return;
		}

		double progress = this.distance / this.maxDistance;
		double baseSpeed = this.maxDistance * 0.5;
		double currentSpeed = baseSpeed * (1.0 - 0.8 * progress);

		this.distance += currentSpeed * elapsedSecond;

		if (this.distance >= this.maxDistance) {
			this.dead = true;
		}
	}

	public void render(Graphics2D g, double centerX, double centerY, double particleDiameter) {
		if (this.dead) {
			return;
		}

		double px = centerX + Math.cos(this.angle) * this.distance - (particleDiameter / 2.0);
		double py = centerY + Math.sin(this.angle) * this.distance - (particleDiameter / 2.0);

		double progress = this.distance / this.maxDistance;
		int alpha = (int)( this.color.getAlpha() * (1.0 - progress * 0.5) );
		alpha = Math.max(0, Math.min(255, alpha));

		g.setColor(new Color(
			this.color.getRed(),
			this.color.getGreen(),
			this.color.getBlue(),
			alpha
		));
		g.fill(new java.awt.geom.Ellipse2D.Double(px, py, particleDiameter, particleDiameter));
	}

	public boolean isDead() {
		return this.dead;
	}
}
