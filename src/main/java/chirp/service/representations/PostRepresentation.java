package chirp.service.representations;

import java.net.URI;

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
	@Link(value=@Ref("/posts/{username}/{timestamp}"), rel="self"),
	@Link(value=@Ref("/posts/{username}"), rel="collection"),
	@Link(value=@Ref("/users/{username}"), rel="related")
})
public class PostRepresentation {

	@Ref("/posts/{username}/{timestamp}")
	private URI self;

	@JsonIgnore
	private final String username;

	private final String timestamp;
	private final String content;

	public PostRepresentation(Post post, boolean summary) {
		timestamp = post.getTimestamp().toString();
		content = summary ? null : post.getContent();
		username = post.getUser().getUsername();
		self = UriBuilder.fromResource(PostsResource.class)
				.path(post.getTimestamp().toString()).build(username);
	}

	@JsonCreator
	public PostRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("timestamp") String timestamp,
			@JsonProperty("content") String content) {
		this.self = self;
		this.username = null;
		this.timestamp = timestamp;
		this.content = content;
	}

	public URI getSelf() {
		return self;
	}

	public String getUsername() {
		return username;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getContent() {
		return content;
	}

}
