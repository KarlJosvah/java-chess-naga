package game.chess.helper;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.FontMetrics;

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
		int textHeight = metrics.getAscent() - metrics.getDescent();

		int x = rect.x;
		int y = rect.y;

		switch (anchor) {
			case TOP_LEFT:
				x = rect.x + margin;
				y = rect.y + margin;
				break;
			case NORTH:
				x = rect.x + (rect.width - textWidth) / 2;
				y = rect.y + margin;
				break;
			case TOP_RIGHT:
				x = rect.x + rect.width - textWidth - margin;
				y = rect.y + margin;
				break;
			case WEST:
				x = rect.x + margin;
				y = rect.y + (rect.height - textHeight) / 2;
				break;
			case CENTER:
				x = rect.x + (rect.width - textWidth) / 2;
				y = rect.y + (rect.height - textHeight) / 2;
				break;
			case EAST:
				x = rect.x + rect.width - textWidth - margin;
				y = rect.y + (rect.height - textHeight) / 2;
				break;
			case BOTTOM_LEFT:
				x = rect.x + margin;
				y = rect.y + rect.height - textHeight - margin;
				break;
			case SOUTH:
				x = rect.x + (rect.width - textWidth) / 2;
				y = rect.y + rect.height - textHeight - margin;
				break;
			case BOTTOM_RIGHT:
				x = rect.x + rect.width - textWidth - margin;
				y = rect.y + rect.height - textHeight - margin;
				break;
		}

		g.drawString(text, x, y + metrics.getAscent());
	}
}