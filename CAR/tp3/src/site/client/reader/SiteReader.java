package site.client.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SiteReader {
	
	private String type;
	
	public String getType() {
		return type;
	}
	
	public Map<Integer, Integer[]> getNodesFromFile(String filename) throws IOException {
		final Map<Integer, Integer[]> nodesToSons = new HashMap<Integer, Integer[]>();
		final BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = null;
		type = reader.readLine();
		while ((line = reader.readLine()) != null) {
			final StringTokenizer tokenizer = new StringTokenizer(line, " ");
			final int nodeId = Integer.parseInt(tokenizer.nextToken());
			final List<Integer> sons = new LinkedList<Integer>();
			while (tokenizer.hasMoreTokens()) {
				sons.add(Integer.parseInt(tokenizer.nextToken()));
			}
			nodesToSons.put(nodeId, sons.toArray(new Integer[sons.size()]));
		}
		reader.close();
		return nodesToSons;
	}
	
}
