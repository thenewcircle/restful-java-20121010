package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.service.resources.PostsResource;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;

@Links({
	@Link(value=@Ref("/posts/{username}"), rel="self"),
	@Link(value=@Ref("/users/{username}"), rel="related")
})
public class PostCollectionRepresentation {

	@Ref("/posts/{username}")
	private URI self;

	@JsonIgnore
	private final String username;

	private final Collection<PostRepresentation> posts;

	public PostCollectionRepresentation(Collection<Post> posts, String username) {
		this.self = UriBuilder.fromResource(PostsResource.class)
				.build(username);
		this.username = username;
		this.posts = new ArrayList<PostRepresentation>();
		for (Post post : posts) {
			this.posts.add(new PostRepresentation(post, true));
		}
	}

	@JsonCreator
	public PostCollectionRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("posts") Collection<PostRepresentation> posts) {
		this.self = self;
		this.username = null;
		this.posts = posts;
	}

	public URI getSelf() {
		return self;
	}

	public String getUsername() {
		return username;
	}

	public Collection<PostRepresentation> getPosts() {
		return posts;
	}

}
