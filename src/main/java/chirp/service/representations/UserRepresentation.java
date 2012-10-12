package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;
import chirp.service.resources.UsersResource;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;

@Links({
	@Link(value=@Ref("/users/{username}"), rel="self"),
	@Link(value=@Ref("/users"), rel="collection"),
	@Link(value=@Ref("/posts/{username}"), rel="related")
})
public class UserRepresentation {

	@Ref("/users/{username}")
	final URI self;

	private final String username;
	private final String realname;

	public UserRepresentation(User user, boolean summary) {
		username = summary ? null : user.getUsername();
		realname = summary ? null : user.getRealname();
		self = UriBuilder.fromResource(UsersResource.class).path(user.getUsername()).build();
	}

	@JsonCreator
	public UserRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("username") String username,
			@JsonProperty("realname") String realname) {
		this.self = self;
		this.username = username;
		this.realname = realname;
	}

	public URI getSelf() {
		return self;
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

}
