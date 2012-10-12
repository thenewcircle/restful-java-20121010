package chirp.service.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.UserCollectionRepresentation;
import chirp.service.representations.UserRepresentation;

import com.google.inject.Inject;

@Path("/users")
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@Context Request request) {
		Collection<User> users = userRepository.getUsers();

		EntityTag eTag = new EntityTag(String.valueOf(users.hashCode()));
		ResponseBuilder response = request.evaluatePreconditions(eTag);
		if (response != null)
			return response.build();

		UserCollectionRepresentation representation = new UserCollectionRepresentation(users);
		return Response.ok(representation).tag(eTag).build();
	}

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserRepresentation getUser(@PathParam("username") String username) {
		User user = userRepository.getUser(username);
		return new UserRepresentation(user, false);
	}

}
