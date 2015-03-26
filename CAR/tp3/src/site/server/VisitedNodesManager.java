package site.server;

import java.util.LinkedList;
import java.util.List;

import site.server.bean.Node;


public class VisitedNodesManager {
	
	private List<Node> visitedSites;
	
	public VisitedNodesManager() {
		visitedSites = new LinkedList<Node>();
	}
	
	public void markAsVisited(Node site) {
		visitedSites.add(site);
	}
	
	public boolean isVisited(Node site) {
		return visitedSites.contains(site);
	}
	
	public void reset() {
		visitedSites.clear();
	}

}
