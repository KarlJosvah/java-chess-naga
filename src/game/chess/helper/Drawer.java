package game.chess.helper;

import java.awt.Graphics2D;
import java.awt.FontMetrics;
import game.chess.utils.Rectangle;

public class Drawer {

	public enum Anchor {
		NORTH,
		SOUTH,
		EAST,
		WEST,
		CENTER,
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT;
	}

	public static void drawStringAnchored(Graphics2D g, String text, Rectangle rect, Drawer.Anchor anchor) {
		Drawer.drawStringAnchored(g, text, rect, anchor, 0);
	}

	public static void drawStringAnchored(Graphics2D g, String text, Rectangle rect, Drawer.Anchor anchor, int margin) {
		if (text == null || text.isEmpty()) {
			return;
		}

		FontMetrics metrics = g.getFontMetrics();
		int textWidth = metrics.stringWidth(text);
		int textHeight = metrics.getHeight();

		int x = rect.getX();
		int y = rect.getY();

		switch (anchor) {
			case TOP_LEFT:
				x = rect.getX() + margin;
				y = rect.getY() + margin;
				break;
			case NORTH:
				x = rect.getX() + (rect.getWidth() - textWidth) / 2;
				y = rect.getY() + margin;
				break;
			case TOP_RIGHT:
				x = rect.getX() + rect.getWidth() - textWidth - margin;
				y = rect.getY() + margin;
				break;
			case WEST:
				x = rect.getX() + margin;
				y = rect.getY() + (rect.getHeight() - textHeight) / 2;
				break;
			case CENTER:
				x = rect.getX() + (rect.getWidth() - textWidth) / 2;
				y = rect.getY() + (rect.getHeight() - textHeight) / 2;
				break;
			case EAST:
				x = rect.getX() + rect.getWidth() - textWidth - margin;
				y = rect.getY() + (rect.getHeight() - textHeight) / 2;
				break;
			case BOTTOM_LEFT:
				x = rect.getX() + margin;
				y = rect.getY() + rect.getHeight() - textHeight - margin;
				break;
			case SOUTH:
				x = rect.getX() + (rect.getWidth() - textWidth) / 2;
				y = rect.getY() + rect.getHeight() - textHeight - margin;
				break;
			case BOTTOM_RIGHT:
				x = rect.getX() + rect.getWidth() - textWidth - margin;
				y = rect.getY() + rect.getHeight() - textHeight - margin;
				break;
		}

		g.drawString(text, x, y + metrics.getAscent());
	}
}