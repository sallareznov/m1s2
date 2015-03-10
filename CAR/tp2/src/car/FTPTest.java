package car;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public abstract class FTPTest {
	
	public void connectToFTP(FTPClient client) throws IOException {
		client.connect("127.0.0.1", 1500);
		client.login("test", "test");
	}
	
	public void disconnectFromFTP(FTPClient client) throws IOException {
		client.disconnect();
	}

}
