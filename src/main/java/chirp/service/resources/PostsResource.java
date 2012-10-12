package chirp.service.resources;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.Post;
import chirp.model.Timestamp;
import chirp.model.UserRepository;
import chirp.service.representations.PostCollectionRepresentation;
import chirp.service.representations.PostRepresentation;

import com.google.inject.Inject;

@Path("/posts/{username}")
public class PostsResource {

	private final UserRepository userRepository;

	@Inject
	public PostsResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@POST
	public Response createPost(@PathParam("username") String username, @FormParam("content") String content) {
		Post post = userRepository.getUser(username).createPost(content);

		URI uri = UriBuilder.fromPath(post.getTimestamp().toString()).build();
		return Response.created(uri).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PostCollectionRepresentation getPosts(@PathParam("username") String username) {
		return new PostCollectionRepresentation(userRepository.getUser(username).getPosts(), username);
	}

	@GET
	@Path("{timestamp}")
	@Produces(MediaType.APPLICATION_JSON)
	public PostRepresentation getPost(@PathParam("username") String username, @PathParam("timestamp") String timestamp) {
		Post post = userRepository.getUser(username).getPost(new Timestamp(timestamp));
		return new PostRepresentation(post, false);
	}

}
