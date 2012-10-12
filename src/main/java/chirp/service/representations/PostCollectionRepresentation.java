package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.service.resources.PostsResource;
import chirp.service.resources.UsersResource;

public class PostCollectionRepresentation {

	private final URI self;
	private final URI user;
	private final Collection<PostRepresentation> posts;

	public PostCollectionRepresentation(Collection<Post> posts, String username) {
		this.self = UriBuilder.fromResource(PostsResource.class)
				.build(username);
		this.user = UriBuilder.fromResource(UsersResource.class).path(username)
				.build();
		this.posts = new ArrayList<PostRepresentation>();
		for (Post post : posts) {
			this.posts.add(new PostRepresentation(post, true));
		}
	}

	@JsonCreator
	public PostCollectionRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("user") URI user,
			@JsonProperty("posts") Collection<PostRepresentation> posts) {
		this.self = self;
		this.user = user;
		this.posts = posts;
	}

	public URI getSelf() {
		return self;
	}

	public URI getUser() {
		return user;
	}

	public Collection<PostRepresentation> getPosts() {
		return posts;
	}

}
