package site.server.bean.graph;

import java.rmi.RemoteException;
import java.util.List;

import site.server.bean.Node;

public interface GraphNode extends Node {
	
	List<GraphNode> getNeighbors() throws RemoteException;
	
	void addNeighbor(GraphNode neighbor) throws RemoteException;
	
	void setNeighbors(List<GraphNode> neighbors) throws RemoteException;

}
