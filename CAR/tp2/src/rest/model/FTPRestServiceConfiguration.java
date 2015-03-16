package rest.model;

public class FTPRestServiceConfiguration {
	
	private String applicationPath;
	private String servicePath;
	private String directorySeparator;
	
	public FTPRestServiceConfiguration(String applicationPath, String servicePath) {
		this.applicationPath = applicationPath;
		this.servicePath = servicePath;
		this.directorySeparator = System.getProperty("file.separator");
	}
	
	public String getApplicationPath() {
		return applicationPath;
	}
	
	public String getServicePath() {
		return servicePath;
	}
	
	public String getDirectorySeparator() {
		return directorySeparator;
	}

}
