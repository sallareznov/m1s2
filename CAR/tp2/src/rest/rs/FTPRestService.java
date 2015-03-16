package rest.rs;

import java.io.IOException;
import java.net.SocketException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rest.services.FTPService;

@Path("/ftp")
public class FTPRestService {
	
	private FTPService service;
	
	public FTPRestService(FTPService service) {
		this.service = service;
	}

	@Produces({ MediaType.TEXT_HTML })
	@GET
	public Response getPeople() throws IOException {
		return service.list(".");
	}
	
	@GET
	@Produces({MediaType.APPLICATION_OCTET_STREAM})
	@Path("/{filename: .*}") //il y a un regex là :D
	public Response getFile( @PathParam("dirname") String dirname ,@PathParam("filename") String filename){
		try {
			if(dirname == null)dirname = "";
			if(filename == null)filename = "";
			return service.getFile(filename);
		} catch (SocketException e) {
		} catch (IOException e) {
		}
		return Response.status(Response.Status.NOT_FOUND).entity("fichier "+filename+" non trouvé").build();
	}

//
//	@Produces({ MediaType.APPLICATION_JSON })
//	@Path("/{email}")
//	@GET
//	public Person getPeople(@PathParam("email") final String email) {
//		return peopleService.getByEmail(email);
//	}
//
//	@Produces({ MediaType.APPLICATION_JSON })
//	@POST
//	public Response addPerson(@Context final UriInfo uriInfo,
//			@FormParam("email") final String email,
//			@FormParam("firstName") final String firstName,
//			@FormParam("lastName") final String lastName) {
//
//		peopleService.addPerson(email, firstName, lastName);
//		return Response.created(
//				uriInfo.getRequestUriBuilder().path(email).build()).build();
//	}
//
//	@Produces({ MediaType.APPLICATION_JSON })
//	@Path("/{email}")
//	@PUT
//	public Person updatePerson(@PathParam("email") final String email,
//			@FormParam("firstName") final String firstName,
//			@FormParam("lastName") final String lastName) {
//
//		final Person person = peopleService.getByEmail(email);
//
//		if (firstName != null) {
//			person.setFirstName(firstName);
//		}
//
//		if (lastName != null) {
//			person.setLastName(lastName);
//		}
//
//		return person;
//	}
//
//	@Path("/{email}")
//	@DELETE
//	public Response deletePerson(@PathParam("email") final String email) {
//		peopleService.removePerson(email);
//		return Response.ok().build();
//	}

}
