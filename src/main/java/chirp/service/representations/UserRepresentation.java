package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;
import chirp.service.resources.UsersResource;

public class UserRepresentation {

	private final URI self;
	private final String username;
	private final String realname;

	public UserRepresentation(User user) {
		username = user.getUsername();
		realname = user.getRealname();
		self = UriBuilder.fromResource(UsersResource.class).path(username).build();
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
