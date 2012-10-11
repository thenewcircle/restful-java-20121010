package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;
import chirp.service.resources.PostsResource;
import chirp.service.resources.UsersResource;

public class UserRepresentation {

	private final URI self;
	private final URI posts;
	private final String username;
	private final String realname;

	public UserRepresentation(User user, boolean summary) {
		username = summary ? null : user.getUsername();
		realname = summary ? null : user.getRealname();
		self = UriBuilder.fromResource(UsersResource.class).path(user.getUsername()).build();
		posts = summary ? null : UriBuilder.fromResource(PostsResource.class).build(username);
	}

	@JsonCreator
	public UserRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("posts") URI posts,
			@JsonProperty("username") String username,
			@JsonProperty("realname") String realname) {
		this.self = self;
		this.posts = posts;
		this.username = username;
		this.realname = realname;
	}

	public URI getSelf() {
		return self;
	}

	public URI getPosts() {
		return posts;
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

}
