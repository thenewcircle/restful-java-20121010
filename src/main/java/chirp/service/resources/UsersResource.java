package chirp.service.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import chirp.model.UserRepository;

@Path("/users")
public class UsersResource {

	private final UserRepository userRepository = UserRepository.getInstance();

	@POST
	public void createUser(@FormParam("username") String username,
			@FormParam("realname") String realname) {
		userRepository.createUser(username, realname);
	}
}
