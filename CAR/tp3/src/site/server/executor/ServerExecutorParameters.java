package site.server.executor;

public class ServerExecutorParameters {
	
	private String nodeType;
	private int nodeId;
	
	public ServerExecutorParameters(String nodeType, int nodeId) {
		this.nodeType = nodeType;
		this.nodeId = nodeId;
	}
	
	public String getNodeType() {
		return nodeType;
	}
	
	public int getNodeId() {
		return nodeId;
	}

}
