package rest.rs;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import rest.model.FTPRestServiceConfiguration;
import rest.services.FTPService;

/**
 * @author diagne
 */
@Path("/repo/")
public class FTPRestService {

	/**
	 */
	private FTPService service;
	/**
	 */
	private FTPRestServiceConfiguration configuration;

	public FTPRestService(FTPService service, String applicationPath) {
		this.service = service;
		final String servicePath = getClass().getAnnotation(Path.class).value();
		this.configuration = new FTPRestServiceConfiguration(applicationPath,
				servicePath);
	}

	@Produces({ MediaType.TEXT_HTML })
	@GET
	public Response listRoot() throws IOException {
		return service.list(".", configuration);
	}

	@GET
	@Produces({ MediaType.TEXT_HTML })
	@Path("/{dirname: .+/}")
	public Response listDirectory(@PathParam("dirname") String dirname) {
		try {
			return service.list(dirname, configuration);
		} catch (SocketException e) {
		} catch (IOException e) {
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{filename: .+}")
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public Response getFile(@PathParam("dirname") String dirname,
			@PathParam("filename") String filename) {
		try {
			if (dirname == null)
				dirname = "";
			if (filename == null)
				filename = "";
			return service.getFile(filename, configuration);
		} catch (SocketException e) {
		} catch (IOException e) {
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{dirname: .*}/{filename: .*}")
	public Response deleteFile(@PathParam("dirname") String dirname,
			@PathParam("filename") String filename) {
		try {
			if (dirname == null)
				dirname = "";
			if (filename == null)
				filename = "";
			return service.deleteFile(dirname + filename);
		} catch (IOException e) {
		}
		return Response.status(Response.Status.BAD_GATEWAY).build();
	}

	@POST
	//@Path("/")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.TEXT_HTML })
	public Response postFile(@PathParam("dirname") String dirname,
			@Multipart("file") MultipartBody file) {
		try {
			dirname = ".";
			String filename = file.getRootAttachment().getDataHandler()
					.getName();
			InputStream fileStream = file.getRootAttachment().getDataHandler()
					.getInputStream();
			return service.upload(dirname, fileStream, filename);
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_GATEWAY)
					.entity("Connexion non disponible").build();
		}
	}

	@PUT
	@Path("/{dirname: .*/}")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.TEXT_HTML })
	public Response putFile(@PathParam("dirname") String dirname,
			@Multipart("file") MultipartBody file) {
		try {
			String filename = file.getRootAttachment().getDataHandler()
					.getName();
			InputStream fileStream = file.getRootAttachment().getDataHandler()
					.getInputStream();
			return service.upload(dirname, fileStream, filename);
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_GATEWAY)
					.entity("Connexion non disponible").build();
		}
	}

}
