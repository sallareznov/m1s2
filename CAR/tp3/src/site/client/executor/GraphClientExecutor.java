package site.client.executor;

import site.client.GraphClientHandler;

public class GraphClientExecutor extends BasicClientExecutor {

	public GraphClientExecutor() {
		super("-graph", new GraphClientHandler());
	}

}
