package site.client.executor;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import site.server.VisitedNodesManager;
import site.server.bean.graph.GraphNode;
import site.server.sending.graph.BFSMessageSending;

public class GraphClientExecutor extends AbstractClientExecutor {

	public GraphClientExecutor() {
		super("GRAPH");
	}

	@Override
	public void execute(ClientExecutorParameters parameters)
			throws InterruptedException, NotBoundException, IOException {
		final String[] args = parameters.getArgs();
		
		final Registry registry = LocateRegistry.getRegistry(args[1], 1099);
		final String[] keys = registry.list();
		final List<GraphNode> nodes = new LinkedList<GraphNode>();
		for (final String key : keys) {
			final GraphNode nodeToAdd = (GraphNode) registry.lookup(key);
			nodes.add(nodeToAdd);
		}
		final GraphNode[] nodesArray = nodes.toArray(new GraphNode[nodes.size()]);
		
		final Map<Integer, Integer[]> nodesToNeighbors = parameters.getNodesToSons();
		for (final Entry<Integer, Integer[]> entry : nodesToNeighbors.entrySet()) {
			for (int i = 0; i < entry.getValue().length; i++) {
				nodesArray[entry.getKey() - 1].addNeighbor(nodesArray[entry.getValue()[i] - 1]);
			}
		}

		final VisitedNodesManager manager = new VisitedNodesManager();
		final GraphNode sender = nodesArray[Integer.parseInt(args[3]) - 1];
		sender.setMessage(args[2]);
		new BFSMessageSending().sendMessage(sender, manager);
		
	}

}
