package site.client.executor;

import site.client.reader.SiteReader;

public class ClientExecutorParameters {
	
	private SiteReader reader;
	private String[] args;
	
	public ClientExecutorParameters(SiteReader reader, String[] args) {
		this.reader = reader;
		this.args = args;
	}
	
	public SiteReader getReader() {
		return reader;
	}
	
	public String[] getArgs() {
		return args;
	}
	
}
