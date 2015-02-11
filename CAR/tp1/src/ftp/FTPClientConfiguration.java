package ftp;

import java.util.Date;

public class FTPClientConfiguration {
    
    private String _username;
    private Date _beginning;
    
    public FTPClientConfiguration() {
	_username = null;
	_beginning = new Date();
    }
    
    public String getUsername() {
	return _username;
    }
    
    public Date getBeginning() {
	return _beginning;
    }
    
    public void setUsername(String username) {
	_username = username;
    }
    
}
