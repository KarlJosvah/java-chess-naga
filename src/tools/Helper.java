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
		int rgb = Integer.parseInt(hexColor, 16);
		return new Color(rgb);
	}
}