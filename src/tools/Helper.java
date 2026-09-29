package tools;

import java.awt.Color;

public class Helper {
	public static void print(Object... toPrint) {
		for (Object obj: toPrint) {
			System.out.print(obj.toString() + "\t\t");
		}
		System.out.println();
	}

	public static Color getColorFromHex(String hexColor) {
		if (hexColor.startsWith("#")) {
			hexColor = hexColor.substring(1);
		}
		if (hexColor.length() == 8) {
			int r = Integer.parseInt(hexColor.substring(0, 2), 16);
			int g = Integer.parseInt(hexColor.substring(2, 4), 16);
			int b = Integer.parseInt(hexColor.substring(4, 6), 16);
			int a = Integer.parseInt(hexColor.substring(6, 8), 16);
			return new Color(r, g, b, a);
		}
		int rgb = (int) Long.parseLong(hexColor, 16);
		return new Color(rgb);
	}

	public static double percent(double nb, double percentage) {
		return nb * percentage / 100;
	}
}