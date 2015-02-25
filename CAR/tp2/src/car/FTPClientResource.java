package car;

import java.io.IOException;
import java.net.SocketException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPListParseEngine;

@Path("/ftpclient")
public class FTPClientResource {

	final FTPClient _client;

	public FTPClientResource() throws IOException {
		_client = new FTPClient();
		_client.connect("127.0.0.1", 1500);
		_client.login("test", "test");
	}

	@GET
	@Produces("text/html")
	public String sayHello() throws IOException {
		final StringBuilder menu = new StringBuilder();
		final String fileSeparator = System.getProperty("file.separator");
		final String workingDirectory = _client.printWorkingDirectory();
		final int separatorLastIndex = workingDirectory.lastIndexOf(fileSeparator);
		final String canonicalPath = _client.printWorkingDirectory().substring(separatorLastIndex);
		menu.append("<h1>Index of " + canonicalPath + "</h1>");
		menu.append("<pre><img src=\"/default_apache_icons/back.gif\" alt=\"[DIR]\"> <a href=\"..\">Parent Directory</a></pre>\"");
//		for (String name : _client.listNames()) {
//			
//		}
		//<pre><img src="/default_apache_icons/blank.gif" alt="Icon "> <a href="?C=N;O=D">Name</a>                    <a href="?C=M;O=A">Last modified</a>      <a href="?C=S;O=A">Size</a>  <a href="?C=D;O=A">Description</a>
		return menu.toString();
	}

	public String getBook(@PathParam("isbn") String isbn)
			throws SocketException, IOException {
		final FTPClient ftp = new FTPClient();

		final FTPListParseEngine engine = ftp
				.initiateListParsing(
						"org.apache.commons.net.ftp.parser.EnterpriseUnixFTPEntryParser",
						".");

		System.out.println(engine.getFiles().length);
		// return stringOf(ftp.list());
		return "";
	}

//	private String stringOf(String[] listNames) {
//		String resultat = "";
//		for (int i = 0; i < listNames.length; i++) {
//			resultat += listNames[i] + "<br>";
//		}
//		return resultat;
//	}

	@GET
	@Path("{var: .*}/stuff")
	public String getStuff(@PathParam("var") String stuff) {
		return "Stuff: " + stuff;
	}
}
