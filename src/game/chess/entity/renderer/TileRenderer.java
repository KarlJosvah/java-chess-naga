package game.chess.entity.renderer;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Ellipse2D;

import tools.Helper;
import engine.tools.AssetsLoader;

import game.chess.entity.Tile;
import game.chess.config.Config;
import game.chess.helper.Drawer;

public class TileRenderer {

// ======================================================================================================================================================

	public static double POSSIBLE_MOVE_CIRCLE_RADIUS_RATE = 20.0;
	public static double CAPTURE_CIRCLE_RADIUS_RATE = 60.0;
	public static double CAPTURE_CIRCLE_THICHNESS_RATE = 10.0;

	public static Color CIRCLE_COLOR = Helper.getColorFromHex("#4b4b4b40");

	public static final Color[] TILE_COLORS = {
		Helper.getColorFromHex("#EBECD0"),
		Helper.getColorFromHex("#779556")
	};

	public static void render(Tile tile, Graphics2D g) {
		switch (tile.getType()) {
			case NORMAL:
				TileRenderer.renderNormal(tile, g);
				break;
			case DISABLED:
				TileRenderer.renderDisabled(tile, g);
				break;
			default:
				TileRenderer.renderNormal(tile, g);
				break;
		}
	}

	public static void renderHighlight(Tile tile, Graphics2D g) {
		switch (tile.getType()) {
			case POSSIBLE_MOVE:
				TileRenderer.renderPossibleMove(tile, g);
				break;
			case CAPTURE:
				TileRenderer.renderCapture(tile, g);
				break;
			case FLAG_RED:
				TileRenderer.renderFlagRed(tile, g);
				break;
			case FLAG_GREEN:
				TileRenderer.renderFlagGreen(tile, g);
				break;
			case FLAG_BLUE:
				TileRenderer.renderFlagBlue(tile, g);
				break;
			case FLAG_ORANGE:
				TileRenderer.renderFlagOrange(tile, g);
				break;
			default:
				break;
		}
	}

// ======================================================================================================================================================

	private static void renderNormal(Tile tile, Graphics2D g) {
		g.setColor(TileRenderer.TILE_COLORS[(tile.getPosition().getRow() + tile.getPosition().getCol() + 1) % 2]);
		g.fillRect(
			tile.getCoordinate().getX(),
			tile.getCoordinate().getY(),
			tile.getSize(),
			tile.getSize()
		);
		TileRenderer.renderAnnotation(tile, g);
	}

	private static void renderDisabled(Tile tile, Graphics2D g) {
		// Do not render
	}

	private static void renderPossibleMove(Tile tile, Graphics2D g) {
		int radius = (int) Helper.percent(tile.getSize(), TileRenderer.POSSIBLE_MOVE_CIRCLE_RADIUS_RATE);
		int x = tile.getCoordinate().getX() + ( (tile.getSize() - radius) / 2 );
		int y = tile.getCoordinate().getY() + ( (tile.getSize() - radius) / 2 );

		g.setColor(TileRenderer.CIRCLE_COLOR);
		g.fillOval(x, y, radius, radius);
	}

	private static void renderCapture(Tile tile, Graphics2D g) {
		int radius = (int) Helper.percent(tile.getSize(), TileRenderer.CAPTURE_CIRCLE_RADIUS_RATE);
		float thickness = (int) Helper.percent(tile.getSize(), TileRenderer.CAPTURE_CIRCLE_THICHNESS_RATE);
		int x = tile.getCoordinate().getX() + ( (tile.getSize() - radius) / 2 );
		int y = tile.getCoordinate().getY() + ( (tile.getSize() - radius) / 2 );

		Graphics2D g2d = (Graphics2D) g.create();
		try {
			g2d.setColor(TileRenderer.CIRCLE_COLOR);
			g2d.setStroke(new BasicStroke(thickness));
			Ellipse2D ring = new Ellipse2D.Double(x, y, radius, radius);
			g2d.draw(ring);
		} finally {
			g2d.dispose();
		}
	}

	private static void renderFlagRed(Tile tile, Graphics2D g) {
	}

	private static void renderFlagGreen(Tile tile, Graphics2D g) {
	}

	private static void renderFlagBlue(Tile tile, Graphics2D g) {
	}

	private static void renderFlagOrange(Tile tile, Graphics2D g) {
	}

// ======================================================================================================================================================

	private static void renderAnnotation(Tile tile, Graphics2D g) {
		g.setColor(TileRenderer.TILE_COLORS[(tile.getPosition().getRow() + tile.getPosition().getCol()) % 2]);

		int paddingX = 4;
		int paddingY = (int) (tile.getSize() * 0.25);
		if (AssetsLoader.font_android_101 != null) {
			g.setFont(AssetsLoader.font_android_101.deriveFont(Font.BOLD, 16f));
		}

		if (Config.get().getTileAnnotation() == Config.TileAnnotation.ALL) {
			Drawer.drawStringAnchored(g, tile.getAnnotation(), tile.getRect(), Drawer.Anchor.TOP_RIGHT, 2);
		} else if (Config.get().getTileAnnotation() == Config.TileAnnotation.BORDER) {
			if (tile.getPosition().getCol() == 0) {
				Drawer.drawStringAnchored(g, tile.getRank(), tile.getRect(), Drawer.Anchor.TOP_LEFT, 2);
			}
			if (tile.getPosition().getRow() == 0) {
				Drawer.drawStringAnchored(g, tile.getFile(), tile.getRect(), Drawer.Anchor.BOTTOM_RIGHT, 2);
			}
		}
	}
}