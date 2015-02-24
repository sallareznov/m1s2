package car;

import java.io.IOException;
import java.net.SocketException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.net.ftp.FTPClient;

@Path("/ftpclient")
public class FTPClientResource {

	@GET
	@Produces("text/html")
	public String sayHello() {
		return "<h1>Hello</h1>";
	}

	 @GET
	 @Path("/ftpclient")
	 public String getBook( @PathParam("isbn") String isbn ) throws SocketException, IOException {
		 final FTPClient ftp = new FTPClient();
		 ftp.connect("127.0.0.1", 1500);
		 ftp.login("test", "test");
		 return stringOf(ftp.listNames());
	 }

	 private String stringOf(String[] listNames) {
		String resultat = "";
		 for (int i = 0; i < listNames.length; i++) {
			resultat += listNames[i] + "<br>";
		}
		return resultat;
	}

	@GET
	 @Path("{var: .*}/stuff")
	 public String getStuff( @PathParam("var") String stuff ) {
		 return "Stuff: "+stuff;
	 }
}
