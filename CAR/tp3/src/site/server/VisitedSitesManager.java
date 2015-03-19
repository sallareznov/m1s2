package site.server;

import java.util.LinkedList;
import java.util.List;

public class VisitedSitesManager {
	
	private List<Site> visitedSites;
	
	public VisitedSitesManager() {
		visitedSites = new LinkedList<Site>();
	}
	
	public void markAsVisited(Site site) {
		visitedSites.add(site);
	}
	
	public boolean isVisited(Site site) {
		return visitedSites.contains(site);
	}
	
	public void reset() {
		visitedSites.clear();
	}

}
