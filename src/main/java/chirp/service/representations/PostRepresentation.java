package chirp.service.representations;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;

public class PostRepresentation {

	private final String timestamp;
	private final String content;

	public PostRepresentation(Post post) {
		timestamp = post.getTimestamp().toString();
		content = post.getContent();
	}

	@JsonCreator
	public PostRepresentation(@JsonProperty("timestamp") String timestamp,
			@JsonProperty("content") String content) {
		this.timestamp = timestamp;
		this.content = content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getContent() {
		return content;
	}

}
