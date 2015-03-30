package site.server.executor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import site.server.bean.tree.TreeNode;
import site.server.bean.tree.TreeNodeImpl;

public class TreeServerExecutor extends AbstractServerExecutor {
	
	public TreeServerExecutor() {
		super("-tree");
	}

	@Override
	public void createAndRebind(ServerExecutorParameters parameters)
			throws RemoteException {
		final int nodeId = parameters.getNodeId();
		final TreeNode treeNode = new TreeNodeImpl(nodeId);
		final TreeNode node = (TreeNode) UnicastRemoteObject.toStub(treeNode);
		LocateRegistry.getRegistry(1099).rebind("node" + nodeId, node);
	}

}
