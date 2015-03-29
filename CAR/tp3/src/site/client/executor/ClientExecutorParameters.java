package site.client.executor;

public class ClientExecutorParameters {
	
	private String nodeType;
	private String serverAddress;
	private String message;
	
	public ClientExecutorParameters(String nodeType, String serverAddress, String message) {
		this.nodeType = nodeType;
		this.serverAddress = serverAddress;
		this.message = message;
	}
	
	public String getNodeType() {
		return nodeType;
	}
	
	public String getServerAddress() {
		return serverAddress;
	}
	
	public String getMessage() {
		return message;
	}
	
}
