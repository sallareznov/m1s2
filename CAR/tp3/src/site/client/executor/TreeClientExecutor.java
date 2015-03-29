package site.client.executor;

import site.client.TreeClientHandler;

public class TreeClientExecutor extends BasicClientExecutor {

	public TreeClientExecutor() {
		super("-tree", new TreeClientHandler());
	}

}
