package coloration.util;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class IntToColour {

	private Map<Integer, String> intToColours;

	public IntToColour() {
		intToColours = new HashMap<Integer, String>();
	}

	public void initColours(String filename) throws IOException {
		intToColours.clear();
		final Properties prop = new Properties();
		final InputStream stream = new FileInputStream(filename);
		prop.load(stream);
		for (final Entry<Object, Object> entry : prop.entrySet()) {
			final String colourInt = (String) entry.getKey();
			final String colourString = (String) entry.getValue();
			intToColours.put(Integer.parseInt(colourInt), colourString);
		}
	}
	
	public String getColourString(Integer colourInt) {
		return intToColours.get(colourInt);
	}

	public static Color hashColor(int value) {
		int r = 0xff - (Math.abs(1 + value) % 0xce);
		int g = 0xff - (Math.abs(1 + value) % 0xdd);
		int b = 0xff - (Math.abs(1 + value) % 0xec);
		System.out.println(r);
		return new Color(r, g, b);
	}

	/**
	 * @return a hex Color string in the format #rrggbb.
	 */
	public static String encodeColor(Color color) {
		return "#" + String.format("%06x", color.getRGB() & 0xffffff);
	}

}
