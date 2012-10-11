package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.service.resources.PostsResource;
import chirp.service.resources.UsersResource;

public class PostRepresentation {

	private final URI self;
	private final URI user;
	private final String timestamp;
	private final String content;

	public PostRepresentation(Post post, boolean summary) {
		timestamp = summary ? null : post.getTimestamp().toString();
		content = summary ? null : post.getContent();
		String username = post.getUser().getUsername();
		self = UriBuilder.fromResource(PostsResource.class).path(post.getTimestamp().toString()).build(username);
		user = summary ? null : UriBuilder.fromResource(UsersResource.class).path(username).build();
	}

	@JsonCreator
	public PostRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("user") URI user,
			@JsonProperty("timestamp") String timestamp,
			@JsonProperty("content") String content) {
		this.self = self;
		this.user = user;
		this.timestamp = timestamp;
		this.content = content;
	}

	public URI getSelf() {
		return self;
	}

	public URI getUser() {
		return user;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getContent() {
		return content;
	}

}
