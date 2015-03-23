package site.server;

import java.io.Serializable;

public interface Site extends Serializable {
	
	int getId();
	
	String getMessage();
	
	void setMessage(String message);
	
	void reset();

}
