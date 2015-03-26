package site.client;

public class ClientExecutorParameters {
	
	private String nodeType;
	private String serverAddress;
	
	public ClientExecutorParameters(String nodeType, String serverAddress) {
		this.nodeType = nodeType;
		this.serverAddress = serverAddress;
	}
	
	public String getNodeType() {
		return nodeType;
	}
	
	public String getServerAddress() {
		return serverAddress;
	}
	
}
