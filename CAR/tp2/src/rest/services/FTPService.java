package rest.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;

import rest.logger.LoggerFactory;
import rest.model.FTPClientFactory;
import rest.model.FTPRestServiceConfiguration;
import rest.model.ItemBuilder;

/**
 * @author diagne
 */
@Service
public class FTPService {

	/**
	 */
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
		final String list = listBuilder.buildList(directory, files,
				configuration);
		client.disconnect();
		return Response.ok(list, MediaType.TEXT_HTML).build();
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

	public Response upload(String dirname, InputStream fileInputStream,
			String filename) throws IOException {
		System.out.println(dirname);
		System.out.println(filename);
		FTPClient client = clientFactory.create();
		boolean directoryFound = true;
		if (dirname != null) {
			directoryFound = client.changeWorkingDirectory(dirname);
		}
		if (!directoryFound) {
			client.disconnect();
			return Response.status(Response.Status.NOT_FOUND)
					.entity("directory " + dirname + " not found").build();
		}
		Response response;
		client.setFileType(FTP.BINARY_FILE_TYPE);
		if (client.storeFile(filename, fileInputStream)) {
			response = Response
					.status(Response.Status.CREATED)
					.entity("File " + filename + " created in directory "
							+ dirname).build();
		} else {
			response = Response
					.status(Response.Status.FORBIDDEN)
					.entity("Not allowed to create " + filename
							+ " in directory " + dirname).build();
		}
		return response;
	}

}
