package site.client.executor;

import site.client.executor.handler.TreeClientHandler;

public class TreeClientExecutor extends AbstractClientExecutor {

	public TreeClientExecutor() {
		super("-tree", new TreeClientHandler());
	}

}
