package site.client.executor;

import java.util.Map;

public class ClientExecutorParameters {
	
	private Map<Integer, Integer[]> nodesToSons;
	private String nodeType;
	private String[] args;
	
	public ClientExecutorParameters(String nodeType, Map<Integer, Integer[]> nodesToSons, String[] args) {
		this.nodeType = nodeType;
		this.nodesToSons = nodesToSons;
		this.args = args;
	}
	
	public Map<Integer, Integer[]> getNodesToSons() {
		return nodesToSons;
	}
	
	public String getNodeType() {
		return nodeType;
	}
	
	public String[] getArgs() {
		return args;
	}
	
}
