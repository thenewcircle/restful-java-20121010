package chirp.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HelloResource {

	@GET
	public String getHello() {
		return "Hello!";
	}
}
