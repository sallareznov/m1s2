package site.client.executor;

import site.client.executor.handler.GraphClientHandler;

public class GraphClientExecutor extends AbstractClientExecutor {

	public GraphClientExecutor() {
		super("-graph", new GraphClientHandler());
	}

}
