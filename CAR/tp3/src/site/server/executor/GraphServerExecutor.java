package site.server.executor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import site.server.bean.graph.GraphNode;
import site.server.bean.graph.GraphNodeImpl;

public class GraphServerExecutor extends AbstractServerExecutor {

	public GraphServerExecutor() {
		super("-graph");
	}

	@Override
	public void createAndRebind(ServerExecutorParameters parameters) throws RemoteException {
		final int nodeId = parameters.getNodeId();
		final GraphNode graphNode = new GraphNodeImpl(nodeId);
		final GraphNode node = (GraphNode) UnicastRemoteObject.toStub(graphNode);
		LocateRegistry.getRegistry(1099).rebind("node" + nodeId, node);
	}

}
