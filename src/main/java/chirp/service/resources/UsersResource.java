package chirp.service.resources;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.UserRepository;

import com.google.inject.Inject;

@Path("users")
public class UsersResource {

	private final UserRepository userRepository;

	@Inject
	public UsersResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@POST
	public Response createUser(@FormParam("username") String username,
			@FormParam("realname") String realname) {
		userRepository.createUser(username, realname);

		URI uri = UriBuilder.fromPath(username).build();
		return Response.created(uri).build();
	}
}
