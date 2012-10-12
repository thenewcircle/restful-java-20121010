package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;

@Links({
	@Link(value = @Ref("/posts/{username}"), rel = "self"),
	@Link(value = @Ref("/users/{username}"), rel = "related")
})
public class PostCollectionRepresentation {

	@Ref("/posts/{username}")
	private URI self;

	private final Collection<PostRepresentation> posts;
	private final String username;

	public PostCollectionRepresentation(Collection<Post> posts, String username) {
		this.posts = new ArrayList<PostRepresentation>();
		for (Post post : posts) {
			this.posts.add(new PostRepresentation(post, true));
		}
		this.username = username;
	}

	@JsonCreator
	public PostCollectionRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("posts") Collection<PostRepresentation> posts) {
		this.self = self;
		this.posts = posts;
		this.username = null;
	}

	public URI getSelf() {
		return self;
	}

	public Collection<PostRepresentation> getPosts() {
		return posts;
	}

	@JsonIgnore
	public String getUsername() {
		return username;
	}
}
