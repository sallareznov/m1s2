package site.server.bean.graph;

import java.rmi.RemoteException;

import site.server.bean.Node;

public interface GraphNode extends Node {
	
	GraphNode[] getNeighbors() throws RemoteException;
	
	void setNeighbors(GraphNode... neighbors) throws RemoteException;

}
