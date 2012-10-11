package chirp.service.resources;

import java.net.URI;
import java.util.Collection;

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

import com.google.inject.Inject;

@Path("posts/{username}")
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
	public Collection<Post> getPosts(@PathParam("username") String username) {
		return userRepository.getUser(username).getPosts();
	}

	@GET
	@Path("{timestamp}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPost(@PathParam("username") String username, @PathParam("timestamp") String timestamp) {
		return userRepository.getUser(username).getPost(new Timestamp(timestamp));
	}

}
