package chirp.service.representations;

import java.net.URI;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;

@Links({
	@Link(value = @Ref("/users/{username}"), rel = "self"),
	@Link(value = @Ref("/users"), rel = "up"),
	@Link(value = @Ref("/posts/{username}"), rel = "related")
})
public class UserRepresentation {

	@Ref("/users/{username}")
	private URI self;

	private final String username;
	private final String realname;

	public UserRepresentation(User user, boolean summary) {
		username = user.getUsername();
		realname = summary ? null : user.getRealname();
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
