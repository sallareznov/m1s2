package rest.model;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FTPClientFactory {

	public FTPClient create(int port, String username, String password)
			throws IOException {
		final FTPClient client = new FTPClient();
		client.connect("127.0.0.1", port);
		client.login(username, password);
		return client;
	}

	public FTPClient create() throws IOException {
		final FTPClient client = new FTPClient();
		client.connect("127.0.0.1", 1500);
		client.login("test", "test");
		return client;
	}

}
