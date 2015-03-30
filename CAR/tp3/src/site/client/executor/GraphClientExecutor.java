package site.client.executor;

import site.client.executor.handler.GraphClientHandler;
import site.client.reader.SiteReader;

public class GraphClientExecutor extends AbstractClientExecutor {

	public GraphClientExecutor(SiteReader reader) {
		super(reader, new GraphClientHandler());
	}

}
