package chirp.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/greeting")
public class HelloResource {

	@GET
	public String getHello() {
		return "Hello!";
	}

	@GET
	@Path("{name}")
	public String getHelloSomebody(@PathParam("name") String name) {
		return "Hello, " + name + "!";
	}
}
