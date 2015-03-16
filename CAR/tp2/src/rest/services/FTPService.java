package rest.services;

import java.io.File;
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
import rest.model.FTPRestServiceConfiguration;
import rest.model.ItemBuilder;

@Service
public class FTPService {

	private FTPClientFactory clientFactory;
	private static final Logger LOGGER = LoggerFactory.create(FTPService.class);

	public FTPService() {
		clientFactory = new FTPClientFactory();
	}

	public boolean isADirectory(String entry) {
		return new File(entry).isDirectory();
	}

	public Response list(String directory,
			FTPRestServiceConfiguration configuration) throws IOException {
		final FTPClient client = clientFactory.create();
		if (!client.changeWorkingDirectory(directory)) {
			client.disconnect();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		LOGGER.info("<--- CWD " + directory);
		LOGGER.info("<--- LIST");
		final FTPFile[] files = client.listFiles();
		final ItemBuilder listBuilder = new ItemBuilder();
		final String test = listBuilder.buildList(directory, files,
				configuration);
		client.disconnect();
		return Response.ok(test, MediaType.TEXT_HTML).build();
	}

	public Response getFile(String filename,
			FTPRestServiceConfiguration configuration) throws IOException {
		final FTPClient client = clientFactory.create();
		Response response;
		if (isADirectory(filename)) {
			return list(filename, configuration);
		}
		LOGGER.info("<--- GET " + filename);
		final InputStream is = client.retrieveFileStream(filename);
		if (is == null) {
			LOGGER.info("ERROR 404 : Not found : " + filename);
			response = Response.status(Response.Status.NOT_FOUND).build();
		} else {
			LOGGER.info("File " + filename + " downloaded successfully");
			response = Response.ok(is, MediaType.APPLICATION_OCTET_STREAM)
					.build();
		}
		client.disconnect();
		return response;
	}

	public Response deleteFile(String filename) throws IOException {
		final FTPClient client = clientFactory.create();
		Response response;
		if (client.deleteFile(filename)) {
			response = Response.ok().build();
		} else {
			LOGGER.info("Cannot delete file " + filename);
			response = Response.status(Response.Status.SERVICE_UNAVAILABLE)
					.build();
		}
		client.disconnect();
		return response;
	}

	public Response upload(String dirname, InputStream fileInputStream)
			throws IOException {
		final FTPClient client = clientFactory.create();
		boolean directoryFound = true;
		if (dirname != null) {
			directoryFound = client.changeWorkingDirectory(dirname);
		}
		if (!directoryFound) {
			client.disconnect();
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Response response;
		String filename = "fileUploaded";
		if (client.storeFile(filename, fileInputStream)) {
			response = Response.status(Response.Status.CREATED).build();
		} else {
			response = Response.status(Response.Status.FORBIDDEN).build();
		}
		return response;
	}

}
