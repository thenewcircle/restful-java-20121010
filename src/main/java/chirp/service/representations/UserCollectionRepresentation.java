package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;
import chirp.service.resources.UsersResource;

public class UserCollectionRepresentation {

	private final URI self;
	private final Collection<UserRepresentation> users;

	public UserCollectionRepresentation(Collection<User> users) {
		this.self = UriBuilder.fromResource(UsersResource.class).build();
		this.users = new ArrayList<UserRepresentation>();
		for (User user : users) {
			this.users.add(new UserRepresentation(user, true));
		}
	}

	@JsonCreator
	public UserCollectionRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("users") Collection<UserRepresentation> users) {
		this.self = self;
		this.users = users;
	}

	public URI getSelf() {
		return self;
	}

	public Collection<UserRepresentation> getUsers() {
		return users;
	}

}
