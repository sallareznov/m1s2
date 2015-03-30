package site.client.executor.handler;

import site.server.bean.tree.TreeNode;

public class MessageSendingProcessParameters {

	private String[] args;
	private TreeNode[] nodes;

	public MessageSendingProcessParameters(String[] args, TreeNode[] nodes) {
		this.args = args;
		this.nodes = nodes;
	}

	public String[] getArgs() {
		return args;
	}

	public TreeNode[] getNodes() {
		return nodes;
	}

}
