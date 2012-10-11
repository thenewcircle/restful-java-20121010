package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.service.resources.PostsResource;

public class PostRepresentation {

	private final URI self;
	private final String timestamp;
	private final String content;

	public PostRepresentation(Post post) {
		timestamp = post.getTimestamp().toString();
		content = post.getContent();
		self = UriBuilder.fromResource(PostsResource.class).path(timestamp).build(post.getUser().getUsername());
	}

	@JsonCreator
	public PostRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("timestamp") String timestamp,
			@JsonProperty("content") String content) {
		this.self = self;
		this.timestamp = timestamp;
		this.content = content;
	}

	public URI getSelf() {
		return self;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getContent() {
		return content;
	}

}
