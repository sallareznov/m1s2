package rest.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;

import rest.logger.LoggerFactory;
import rest.model.FTPClientFactory;
import car.ItemBuilder;

@Service
public class FTPService {

	private FTPClientFactory clientFactory;
	private static final String DIRECTORY_SEPARATOR = System
			.getProperty("file.separator");
	private static final Logger LOGGER = LoggerFactory.create(FTPService.class);

	public FTPService() {
		clientFactory = new FTPClientFactory();
	}

	public Response list(String directory) throws IOException {
		LOGGER.info("<--- LIST");
		final FTPClient client = clientFactory.create();
		if (!client.changeWorkingDirectory(directory)) {
			client.disconnect();
			return Response.status(Response.Status.NOT_FOUND)
					.entity("ERROR 404 : Not found : " + directory).build();
		}
		final FTPFile[] files = client.listFiles();
		final ItemBuilder listBuilder = new ItemBuilder();
		final String test = listBuilder.buildList(directory, files, DIRECTORY_SEPARATOR);
		client.disconnect();
		return Response.ok(test, MediaType.TEXT_HTML).build();
	}

	public Response getFile(String filename) throws IOException {
		LOGGER.info("<--- GET " + filename);
		final FTPClient client = clientFactory.create();
		Response response;
		final InputStream is = client.retrieveFileStream(filename);
		// il faut traiter si c'est un dossier
		if (is == null) {
			System.out.println("ERROR 404 : Not found : " + filename);
			response = Response.status(Response.Status.NOT_FOUND)
					.entity("ERROR 404 : Not found : " + filename + "<br>")
					.build();
		} else {
			response = Response.ok(is, MediaType.APPLICATION_OCTET_STREAM)
					.build();
		}
		client.disconnect();
		return response;
	}

}
