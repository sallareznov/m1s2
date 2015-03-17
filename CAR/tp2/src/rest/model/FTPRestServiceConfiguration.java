package rest.model;

/**
 * @author  diagne
 */
public class FTPRestServiceConfiguration {
	
	/**
	 */
	private String applicationPath;
	/**
	 */
	private String servicePath;
	/**
	 */
	private String directorySeparator;
	
	public FTPRestServiceConfiguration(String applicationPath, String servicePath) {
		this.applicationPath = applicationPath;
		this.servicePath = servicePath;
		this.directorySeparator = System.getProperty("file.separator");
	}
	
	/**
	 * @return
	 */
	public String getApplicationPath() {
		return applicationPath;
	}
	
	/**
	 * @return
	 */
	public String getServicePath() {
		return servicePath;
	}
	
	/**
	 * @return
	 */
	public String getDirectorySeparator() {
		return directorySeparator;
	}

}
