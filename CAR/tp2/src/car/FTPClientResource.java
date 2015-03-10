package car;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.net.ftp.FTPClient;

@Path("/ftp")
public class FTPClientResource extends FTPTest {

	@GET
	@Produces("text/html")
	public String sayHello() throws IOException {
		final FTPClient client = new FTPClient();
		connectToFTP(client);
		final String fileSeparator = System.getProperty("file.separator");
		final String workingDirectory = client.printWorkingDirectory();
		final int separatorLastIndex = workingDirectory.lastIndexOf(fileSeparator);
		final String canonicalPath = client.printWorkingDirectory().substring(separatorLastIndex);
		final ItemBuilder itemBuilder = new ItemBuilder();
		final String[] names = client.listNames();
		disconnectFromFTP(client);
		return itemBuilder.buildList(names, canonicalPath);
	}

	@GET
	@Path("{var: .*}/stuff")
	public String getStuff(@PathParam("var") String stuff) {
		return "Stuff: " + stuff;
	}
}
