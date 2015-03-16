package car;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import rest.model.FTPClientFactory;

public class Dumb {

	public static void main(String[] args) throws IOException {
		final FTPClientFactory clientFactory = new FTPClientFactory();
		final FTPClient client = clientFactory.create();
	}

}
