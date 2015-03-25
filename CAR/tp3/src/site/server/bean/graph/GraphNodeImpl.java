package site.server.bean.graph;

import java.rmi.RemoteException;

import site.server.bean.AbstractNode;

public class GraphNodeImpl extends AbstractNode implements GraphNode {
	
	private static final long serialVersionUID = -7690340498334817408L;
	private GraphNode[] neighbors;
	
	public GraphNodeImpl(int id) throws RemoteException {
		super(id);
		this.neighbors = null;
	}
	
	@Override
	public GraphNode[] getNeighbors() {
		return neighbors;
	}
	
	@Override
	public void setNeighbors(GraphNode... neighbors) throws RemoteException {
		this.neighbors = neighbors;
	}

}
