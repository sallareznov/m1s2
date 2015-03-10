package car;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.net.ftp.FTPClient;

@Path("/ftp/{filename}")
public class FTPFileResource extends FTPTest {
	
	@GET
	@Produces("application/octet-stream")
	public Response retrieveFile(@PathParam("filename") String filename) throws IOException {
		final FTPClient client = new FTPClient();
		connectToFTP(client);
		final InputStream inputStream = client.retrieveFileStream(filename);
		disconnectFromFTP(client);
		if (inputStream == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("ERROR 404 : " + filename + " not found").build(); 
		}
		return Response.ok(inputStream, MediaType.APPLICATION_OCTET_STREAM).build();
	}

}
