package chirp.service.representations;

import java.net.URI;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;

@Links({
	@Link(value = @Ref("/posts/{username}/{timestamp}"), rel = "self"),
	@Link(value = @Ref("/posts/{username}"), rel = "up"),
	@Link(value = @Ref("/users/{username}"), rel = "related")
})
public class PostRepresentation {

	@Ref("/posts/{username}/{timestamp}")
	private URI self;

	private final String timestamp;
	private final String content;
	private final String username;

	public PostRepresentation(Post post, boolean summary) {
		timestamp = post.getTimestamp().toString();
		content = summary ? null : post.getContent();
		username = post.getUser().getUsername();
	}

	@JsonCreator
	public PostRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("timestamp") String timestamp,
			@JsonProperty("content") String content) {
		this.self = self;
		this.timestamp = timestamp;
		this.content = content;
		this.username = null;
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

	@JsonIgnore
	public String getUsername() {
		return username;
	}
}
