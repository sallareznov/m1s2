package site.client.executor;

import site.client.executor.handler.TreeClientHandler;
import site.client.reader.SiteReader;

public class TreeClientExecutor extends AbstractClientExecutor {

	public TreeClientExecutor(SiteReader reader) {
		super(reader, new TreeClientHandler());
	}

}
