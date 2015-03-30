package site.server;

import java.util.LinkedList;
import java.util.List;

import site.server.bean.Node;

/**
 * Manages the visited nodes
 */
public class VisitedNodesManager {
	
	private List<Node> visitedNodes;
	
	/**
	 * Default constructor
	 */
	public VisitedNodesManager() {
		visitedNodes = new LinkedList<Node>();
	}
	
	/**
	 * marks a node as visited 
	 * @param node the node to mark
	 */
	public void markAsVisited(Node node) {
		visitedNodes.add(node);
	}
	
	/**
	 * checks if a node is visited
	 * @param node the node
	 * @return <code>true</code> if the node is visited
	 */
	public boolean isVisited(Node node) {
		return visitedNodes.contains(node);
	}
	
	/**
	 * unmark all nodes
	 */
	public void reset() {
		visitedNodes.clear();
	}

}
