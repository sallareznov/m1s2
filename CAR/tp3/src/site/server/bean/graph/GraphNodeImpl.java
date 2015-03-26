package site.server.bean.graph;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import site.server.bean.AbstractNode;

public class GraphNodeImpl extends AbstractNode implements GraphNode {
	
	private static final long serialVersionUID = -7690340498334817408L;
	private transient List<GraphNode> neighbors;
	
	public GraphNodeImpl(int id) throws RemoteException {
		super(id);
		this.neighbors = new LinkedList<GraphNode>();
	}
	
	@Override
	public List<GraphNode> getNeighbors() throws RemoteException {
		return neighbors;
	}
	
	@Override
	public void addNeighbor(GraphNode neighbor) throws RemoteException {
		neighbors.add(neighbor);
	}
	
	@Override
	public void setNeighbors(List<GraphNode> neighbors) throws RemoteException {
		this.neighbors = neighbors;
		for (final GraphNode neighbor : neighbors) {
			neighbor.addNeighbor(this);
		}
	}

}
