package coloration.util;

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

}
