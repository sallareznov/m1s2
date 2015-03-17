package rest.rs;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")
public class FTPLoginService {

	public String sayHello() {
		return "<form method=\"POST\" action=\"login\"> Email Address: <input type=\"text\" name=\"username\"> <br> Password: <input type=\"text\" name=\"password\"> <input type=\"submit\">	  </form>";
	}

	@Path("/login")
	@POST
	public String login(@FormParam("username") String e,
			@FormParam("password") String p) {
		return "Logged with " + e + " " + p;
	}

}
