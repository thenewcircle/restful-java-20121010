package chirp.service.representations;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;

public class UserRepresentation {

	private final String username;
	private final String realname;

	public UserRepresentation(User user) {
		username = user.getUsername();
		realname = user.getRealname();
	}

	@JsonCreator
	public UserRepresentation(@JsonProperty("username") String username,
			@JsonProperty("realname") String realname) {
		this.username = username;
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

}
