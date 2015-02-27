package lettre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DicoReader {
	
	public List<String> read(String filename) throws IOException {
		final BufferedReader reader = new BufferedReader(new FileReader(filename));
		final List<String> mots = new LinkedList<String>();
		String motCourant = null;
		while ((motCourant = reader.readLine()) != null) {
			mots.add(motCourant);
		}
		return mots;
	}

}
